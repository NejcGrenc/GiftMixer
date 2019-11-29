package grenc.giftmixer.backend.model.rest;

public class RestResponse<T> {

	public RestResponse() {}
	public RestResponse(boolean success, T value) {
		this.success = success;
		this.value = value;
	}

	public T value;
	public boolean success;

	public static <T> RestResponse<T> success(T value) {
		return new RestResponse<T>(true, value);
	}
	public static <T> RestResponse<T> fail() {
		return new RestResponse<T>(false, null);
	}
	public static <T> RestResponse<T> fail(T value) {
		return new RestResponse<T>(false, value);
	}
}
