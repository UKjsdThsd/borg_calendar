package net.sf.borg.ui.calendar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.sf.borg.common.PrefName;
import net.sf.borg.common.Prefs;
import net.sf.borg.common.Resource;
import net.sf.borg.model.AppointmentModel;
import net.sf.borg.model.beans.Appointment;
import net.sf.borg.ui.MultiView;

// ApptDayBox holds the logical information needs to determine
// how an appointment box should be drawn in a day grid
public class ApptBox implements Box{

    // compare boxes by number of overlaps
    private static class boxcompare implements Comparator {

	public int compare(java.lang.Object obj, java.lang.Object obj1) {
	    ApptBox so1 = (ApptBox) obj;
	    ApptBox so2 = (ApptBox) obj1;
	    int diff = so2.getMaxAcrossAtOneTime() - so1.getMaxAcrossAtOneTime();
	    if (diff != 0)
		return (diff);

	    return (1);
	}

    }
    
    final static private BasicStroke highlight = new BasicStroke(2.0f);

    final static private float hlthickness = 2.0f;

    final static private int radius = 5;

    
    
    // create an ApptDatBoxLayout and layout the appts
    static public void layoutBoxes(Collection boxlist, int starthr, int endhr) {
	TreeSet boxtree = new TreeSet(new boxcompare());
	double startmin = starthr * 60;
	double endmin = endhr * 60;

	// initialize the boxes
	Iterator it = boxlist.iterator();
	while (it.hasNext()) {

	    ApptBox box = (ApptBox) it.next();
	    Appointment ap = box.appt;
	    Date d = ap.getDate();
	    if (d == null)
		continue;

	    // determine appt start and end minutes
	    GregorianCalendar acal = new GregorianCalendar();
	    acal.setTime(d);
	    double apstartmin = 60 * acal.get(Calendar.HOUR_OF_DAY) + acal.get(Calendar.MINUTE);
	    int dur = 0;
	    Integer duri = ap.getDuration();
	    if (duri != null) {
		dur = duri.intValue();
	    }
	    double apendmin = apstartmin + dur;

	    if (apstartmin < startmin)
		apstartmin = startmin;
	    if (apendmin > endmin)
		apendmin = endmin;
	    box.setTopAdjustment((apstartmin - startmin) / (endmin - startmin));
	    // adjust the bottom ever so slightly that appts that touch top
	    // to bottom
	    // do not get detected as overlapping when rounding errors creep
	    // in
	    box.setBottomAdjustment(((apendmin - startmin) / (endmin - startmin)) - 1.0 / 10000); 

	}

	// determine the overlaps for each appt
	for (int t = (int) startmin; t <= (int) endmin; t += 5) {
	    ArrayList lst = new ArrayList();
	    Iterator it1 = boxlist.iterator();
	    while (it1.hasNext()) {

		ApptBox curBox = (ApptBox) it1.next();
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(curBox.appt.getDate());
		int amin = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		if (amin <= t && (amin + curBox.appt.getDuration().intValue()) > t)
		    lst.add(curBox);

	    }

	    it1 = lst.iterator();
	    while (it1.hasNext()) {

		ApptBox curBox = (ApptBox) it1.next();
		curBox.setMaxAcrossAtOneTime(Math.max(curBox.getMaxAcrossAtOneTime(), lst.size()));
	    }
	}

	// sort the list
	boxtree.addAll(boxlist);

	// determine left and right for each box by placing boxes on the grid
	// one at a time
	Iterator it1 = boxtree.iterator();
	while (it1.hasNext()) {
	    // curBox is the one we are trying to place in the grid
	    ApptBox curBox = (ApptBox) it1.next();

	    Iterator it2 = boxtree.iterator();
	    double maxRightOfPlaced = 0; // farthest right edge of any placed
	    // appts that overlap the current
	    while (it2.hasNext()) {
		ApptBox otherBox = (ApptBox) it2.next();
		if (otherBox == curBox)
		    continue;

		// detect overlap
		if (otherBox.getTopAdjustment() > curBox.getBottomAdjustment()
			|| otherBox.getBottomAdjustment() < curBox.getTopAdjustment()) {
		    // no overlap
		    continue;
		}

		if (otherBox.getMaxAcrossAtOneTime() < curBox.getMaxAcrossAtOneTime())
		    otherBox.setMaxAcrossAtOneTime(curBox.getMaxAcrossAtOneTime());

		if (otherBox.isPlaced() && otherBox.getRightAdjustment() > maxRightOfPlaced) {
		    maxRightOfPlaced = otherBox.getRightAdjustment();
		}
	    }

	    if (maxRightOfPlaced >= 0.999) {
		// cannot place here
		// need to fine a "hole" in the grid
		for (int slot = 0; slot < curBox.getMaxAcrossAtOneTime(); slot++) {

		    // check if appt is in this slot
		    boolean slotTaken = false;
		    Iterator pi = boxtree.iterator();
		    while (pi.hasNext()) {
			ApptBox otherBox = (ApptBox) pi.next();
			if (otherBox == curBox)
			    continue;
			if (!otherBox.isPlaced())
			    continue;
			if (otherBox.getTopAdjustment() > curBox.getBottomAdjustment()
				|| otherBox.getBottomAdjustment() < curBox.getTopAdjustment()) {
			    // no overlap
			    continue;
			}

			// determine if the appt is in this slot
			if (Math.abs(otherBox.getLeftAdjustment() - ((double) slot) / (double) otherBox.getMaxAcrossAtOneTime()) < 0.001) {
			    slotTaken = true;
			    break;
			}
		    }

		    if (!slotTaken) {

			curBox.setLeftAdjustment((double) slot / (double) curBox.getMaxAcrossAtOneTime());
			curBox.setRightAdjustment(curBox.getLeftAdjustment() + (1 / (double) curBox.getMaxAcrossAtOneTime()));
			curBox.setPlaced(true);
			break;
		    }

		}

	    }

	    if (curBox.isPlaced())
		continue;

	    curBox.setLeftAdjustment(maxRightOfPlaced);
	    curBox.setRightAdjustment(curBox.getLeftAdjustment() + (1 / (double) curBox.getMaxAcrossAtOneTime()));
	    curBox.setPlaced(true);
	}
	
	it = boxlist.iterator();
	while (it.hasNext()) {
	    ApptBox b = (ApptBox) it.next();
	    Rectangle r = new Rectangle();
	    
	    r.x = (int) (b.bounds.x + b.bounds.width * b.getLeftAdjustment());
	    r.y = (int) (b.bounds.y + b.bounds.height * b.getTopAdjustment());
	    r.height = (int) ((b.getBottomAdjustment() - b.getTopAdjustment()) * b.bounds.height);
	    r.width = (int) ((b.getRightAdjustment() - b.getLeftAdjustment()) * b.bounds.width);
	    b.setBounds(r);
	}

    }


    

    // the right side of the box

    private Appointment appt = null;

    // the right side of the box should be drawn

    private Color bordercolor[] = null;

    // the top side of the box should be drawn

    private double bottom; // fraction of the available grid height at which

    // the bottom side of the box should be drawn

    private Rectangle bounds, clip;

    // one at a particular time

    private Color boxcolor[] = null;

    private int boxnum;

    private Date date; // date being displayed - not necessarily date of

    private double endmin;

    private boolean isPlaced = false; // whether or not this box has been

    private boolean isSelected = false;

    // during the layout process
    private double left; // fraction of the available grid width at which

    private int maxAcrossAtOneTime = 0; // max number of appts overlapping this

    // should be drawn
    private double right; // fraction of the available grid width at which

    // appt
    private double startmin;
    
    private Icon todoIcon = null;

    private double top; // fraction of the available grid height at which

    public ApptBox(Date d,Appointment ap, double sm, double em, Rectangle bounds, Rectangle clip) {
	startmin = sm;
	endmin = em;
	appt = ap;
	date = d;
	this.bounds = bounds;
	this.clip = clip;
	todoIcon = null;
	String iconname = Prefs.getPref(PrefName.UCS_MARKER);
	String use_marker = Prefs.getPref(PrefName.UCS_MARKTODO);
	if (use_marker.equals("true") && (iconname.endsWith(".gif") || iconname.endsWith(".jpg"))) {
	    todoIcon = new javax.swing.ImageIcon(getClass().getResource("/resource/" + iconname));
	}
    }

    public void delete() {
	AppointmentModel.getReference().delAppt(appt.getKey());
    }

    public void draw(Graphics2D g2,Component comp) {
	Stroke stroke = g2.getStroke();
	Shape s = g2.getClip();
	if (clip != null)
	    g2.setClip(clip);

	Font sm_font = g2.getFont();
	Map stmap = new HashMap();
	stmap.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
	stmap.put(TextAttribute.FONT, sm_font);
	

	// fill the box with color
	g2.setColor(getBoxColor(boxnum));
	// g2.fill3DRect(b.bounds.x, b.bounds.y,
	// b.bounds.width, b.bounds.height, true);
	g2.fillRoundRect(bounds.x + (int) hlthickness, bounds.y + (int) hlthickness, bounds.width - (int) hlthickness,
		bounds.height - (int) hlthickness, radius * radius, radius * radius);
	g2.setColor(getBorderColor(boxnum));
	g2.setStroke(highlight);
	if (isSelected()) {
	    g2.setColor(Color.CYAN);
	}
	g2.drawRoundRect(bounds.x + (int) hlthickness, bounds.y + (int) hlthickness, bounds.width - (int) hlthickness,
		bounds.height - (int) hlthickness, radius * radius, radius * radius);
	g2.setStroke(stroke);

	// draw the appt text
	g2.setColor(Color.BLACK);
	g2.clipRect(bounds.x, bounds.y, bounds.width, bounds.height);

	// add a single appt text

	// change color for a single appointment based on
	// its color - only if color print option set
	g2.setColor(Color.black);
	if (getTextColor().equals("red"))
	    g2.setColor(new Color(204, 0, 51));
	else if (getTextColor().equals("green"))
	    g2.setColor(new Color(0, 153, 0));
	else if (getTextColor().equals("blue"))
	    g2.setColor(new Color(102, 0, 204));
	if (isTodo() && todoIcon != null) {
	    todoIcon.paintIcon(comp, g2, bounds.x + radius, bounds.y + radius + 8);
	    drawWrappedString(g2, getText(), bounds.x + radius + todoIcon.getIconWidth(), bounds.y + radius, bounds.width - radius);
	} else {
	    drawWrappedString(g2, getText(), bounds.x + radius, bounds.y + radius, bounds.width - radius);
	}

	g2.setClip(s);
	g2.setColor(Color.black);
    }

    public void edit() {
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(date);
	AppointmentListView ag = new AppointmentListView(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	MultiView.getMainView().addView(ag);
	ag.showApp(appt.getKey());

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Date getDate() {
        return date;
    }
    
 
    public String getText() {
	return appt.getText();
    }

    public void move(double y_fraction, Date d) throws Exception {
	// calculate new start hour or duration and update appt
	int realtime = ApptBoxPanel.realMins(y_fraction, startmin, endmin);
	int hour = realtime / 60;
	int min = realtime % 60;

	// get appt from DB - one cached here has time prepended to text by
	// Day.getDayInfo()
	Appointment ap = AppointmentModel.getReference().getAppt(appt.getKey());
	// Date oldTime = ap.getDate();
	int oldkey = ap.getKey();

	GregorianCalendar newCal = new GregorianCalendar();
	// System.out.println(d);
	newCal.setTime(d);
	newCal.set(Calendar.HOUR_OF_DAY, hour);
	int roundMin = (min / 5) * 5;
	newCal.set(Calendar.MINUTE, roundMin);
	int newkey = AppointmentModel.dkey(newCal);
	Date newTime = newCal.getTime();
	ap.setDate(newTime);
	if (oldkey != newkey) { // date chg
	    AppointmentModel.getReference().delAppt(ap.getKey());
	    AppointmentModel.getReference().saveAppt(ap, true);
	} else {
	    AppointmentModel.getReference().saveAppt(ap, false);
	}
    }

    public void resize(boolean isTop, double y_fraction) throws Exception {
	// calculate new start hour or duration and update appt
	int realtime = ApptBoxPanel.realMins(y_fraction, startmin, endmin);
	int hour = realtime / 60;
	int min = realtime % 60;

	if (isTop) {
	    // get appt from DB - one cached here has time prepended to text by
	    // Day.getDayInfo()
	    Appointment ap = AppointmentModel.getReference().getAppt(appt.getKey());
	    Date oldTime = ap.getDate();
	    GregorianCalendar newCal = new GregorianCalendar();
	    newCal.setTime(oldTime);
	    newCal.set(Calendar.HOUR_OF_DAY, hour);
	    int roundMin = (min / 5) * 5;
	    newCal.set(Calendar.MINUTE, roundMin);
	    Date newTime = newCal.getTime();
	    int newDur = ap.getDuration().intValue() + ((int) (oldTime.getTime() - newTime.getTime()) / (1000 * 60));
	    // System.out.println( newTime.toString() + " " + newDur);
	    if (newDur < 5)
		return;
	    ap.setDate(newTime);
	    ap.setDuration(new Integer(newDur));
	    AppointmentModel.getReference().saveAppt(ap, false);
	} else {
	    // get appt from DB - one cached here has time prepended to text by
	    // Day.getDayInfo()
	    Appointment ap = AppointmentModel.getReference().getAppt(appt.getKey());
	    Date start = ap.getDate();
	    long endtime = start.getTime() + (60 * 1000) * ap.getDuration().intValue();
	    Date oldEnd = new Date(endtime);
	    // System.out.println("oldend=" + oldEnd);
	    Calendar newEnd = new GregorianCalendar();
	    newEnd.setTime(oldEnd);
	    newEnd.set(Calendar.HOUR_OF_DAY, hour);
	    int roundMin = (min / 5) * 5;
	    newEnd.set(Calendar.MINUTE, roundMin);
	    // System.out.println("newEnd=" + newEnd.getTime());
	    // System.out.println("start=" + start.getTime());
	    int newDur = (int) (newEnd.getTime().getTime() - start.getTime()) / (1000 * 60);
	    // System.out.println( "newDur=" + newDur);
	    if (newDur < 5)
		return;
	    ap.setDuration(new Integer(newDur));
	    AppointmentModel.getReference().saveAppt(ap, false);
	}
    }

    public void setBoxnum(int boxnum) {
        this.boxnum = boxnum;
    }

    public void setSelected(boolean isSelected) {
	this.isSelected = isSelected;
    }

    private void drawWrappedString(Graphics2D g2, String tx, int x, int y, int w) {
	int fontDesent = g2.getFontMetrics().getDescent();
	HashMap hm = new HashMap();
	hm.put(TextAttribute.FONT, g2.getFont());
	AttributedString as = new AttributedString(tx, hm);
	AttributedCharacterIterator para = as.getIterator();
	int start = para.getBeginIndex();
	int endi = para.getEndIndex();
	LineBreakMeasurer lbm = new LineBreakMeasurer(para, new FontRenderContext(null, false, false));
	lbm.setPosition(start);
	int tt = y + 2;
	while (lbm.getPosition() < endi) {
	    TextLayout tlayout = lbm.nextLayout(w - (2 * fontDesent));
	    tt += tlayout.getAscent();
	    tlayout.draw(g2, x + 2, tt);
	    tt += tlayout.getDescent() + tlayout.getLeading();
	}
    }

    private Color getBorderColor(int i) {
	if (bordercolor == null) {
	    bordercolor = new Color[4];
	    bordercolor[0] = new Color(255, 100, 100);
	    bordercolor[1] = new Color(100, 100, 255);
	    bordercolor[3] = new Color(255, 255, 100);
	    bordercolor[2] = new Color(100, 255, 100);
	}

	return bordercolor[i % bordercolor.length];
    }

    private double getBottomAdjustment() {
	return bottom;
    }

    private Color getBoxColor(int i) {
	if (boxcolor == null) {
	    boxcolor = new Color[4];
	    boxcolor[0] = new Color(255, 235, 235);
	    boxcolor[1] = new Color(235, 235, 255);
	    boxcolor[3] = new Color(255, 255, 235);
	    boxcolor[2] = new Color(235, 255, 235);
	}

	return boxcolor[i % boxcolor.length];
    }

    private double getLeftAdjustment() {
	return left;
    }

    private int getMaxAcrossAtOneTime() {
	return maxAcrossAtOneTime;
    }

    private double getRightAdjustment() {
	return right;
    }

    private String getTextColor() {
	if (appt == null)
	    return null;

	if ((appt.getColor() != null && appt.getColor().equals("strike"))
		|| (appt.getTodo() && !(appt.getNextTodo() == null || !appt.getNextTodo().after(date)))) {
	    return ("strike");
	}
	return appt.getColor();
    }

    private double getTopAdjustment() {
	return top;
    }

    private boolean isPlaced() {
	return isPlaced;
    }

    private boolean isSelected() {
	return isSelected;
    }

    private boolean isTodo() {
	return appt.getTodo();
    }

    private void setBottomAdjustment(double bottom) {
	this.bottom = bottom;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    private void setLeftAdjustment(double left) {
	this.left = left;
    }

    private void setMaxAcrossAtOneTime(int maxAcrossAtOneTime) {
	this.maxAcrossAtOneTime = maxAcrossAtOneTime;
    }

    private void setPlaced(boolean isPlaced) {
	this.isPlaced = isPlaced;
    }
    private void setRightAdjustment(double right) {
	this.right = right;
    }

    private void setTopAdjustment(double top) {
	this.top = top;
    }

    private JPopupMenu popmenu = null;
    public JPopupMenu getMenu() {
	JMenuItem mnuitm;
	if( popmenu == null )
	{
	    popmenu = new JPopupMenu();
	    popmenu.add(mnuitm = new JMenuItem(Resource.getPlainResourceString("Edit")));
	    mnuitm.addActionListener(new ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {	    
		    edit();	   
		}
	    });
	    popmenu.add(mnuitm = new JMenuItem(Resource.getPlainResourceString("Delete")));
	    mnuitm.addActionListener(new ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		    delete();
		}
	    });
	}
	return popmenu;
    }

}