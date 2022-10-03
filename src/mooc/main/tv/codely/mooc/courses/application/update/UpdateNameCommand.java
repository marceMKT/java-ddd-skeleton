package tv.codely.mooc.courses.application.update;

public class UpdateNameCommand {
	
	private String id;
	private String newName;
	
	public UpdateNameCommand(String id, String newName) {
		this.id = id;
		this.newName =  newName;
	}
	
	public String getId() {
		return id;
	}

	public String getNewName() {
		return newName;
	}
	
}
