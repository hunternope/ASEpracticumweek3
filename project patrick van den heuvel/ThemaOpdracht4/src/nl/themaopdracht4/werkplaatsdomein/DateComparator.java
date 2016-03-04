/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 28 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Date> {
	@Override
	public int compare(Date o1, Date o2) {
		if (o1 != null && o2 != null) {
			if (o1.before(o2)) {
				return -1;
			} else if (o1.after(o2)) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;	
	} 
}
