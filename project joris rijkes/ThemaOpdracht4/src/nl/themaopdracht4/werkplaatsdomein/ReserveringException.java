//Ingo van Leeuwen Copyright
package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

	public class ReserveringException extends Exception implements Serializable {
		
	

		/**
		 * 
		 */
		private static final long serialVersionUID = -7758091358924254655L;

		public ReserveringException() {
			
			super();
			
		}
		
		public ReserveringException(String msg) {
			
			super(msg);
			
		}

	}


