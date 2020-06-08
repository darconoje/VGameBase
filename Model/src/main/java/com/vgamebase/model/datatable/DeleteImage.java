package com.vgamebase.model.datatable;

public class DeleteImage {

	private boolean error;
	
	public DeleteImage() {
		
	}

	public DeleteImage(boolean error) {
		this.error = error;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
}
