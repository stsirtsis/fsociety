package gr.ntua.ece.softeng.error;

public class CustomResponse<T> {
	private T message;

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}
	
	
}
