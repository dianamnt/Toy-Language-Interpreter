package exceptions;

public class FileExceptions extends RuntimeException{
	private String message;
	public FileExceptions(String msg){
		this.message = msg;
	}
	public String getMessage()
	{
		return this.message;
	}
}

