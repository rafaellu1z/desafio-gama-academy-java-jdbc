package util;


	/**
	 * @author Gama Academy
	 * 
	 */
	public class TrataException extends Exception {

	    public TrataException(String mensagem, Exception e) {
	        super(mensagem, e);
	    }

	    public TrataException(String mensagem) {
	        super(mensagem);
	    }

	    public static void print(Exception e, String mensagem) {
	        System.out.println("==============Exception===============");
	        System.out.println(mensagem);
	        System.out.println("PrintStackTrace: ");
	        e.printStackTrace();
	        System.out.println("============End Exception=============");
	    }

	    public void print() {
	        System.out.println("==============Exception===============");
	        System.out.println(getMessage());
	        System.out.println("PrintStackTrace: ");
	        getCause().printStackTrace();
	        System.out.println("============End Exception=============");
	    }	

}
