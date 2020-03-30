package exceptions;

public class EmptySatck extends RuntimeException{
	private String message;
	public EmptySatck(String msg){
		this.message = msg;
	}
	public String getMessage()
	{
		return this.message;
	}
}
