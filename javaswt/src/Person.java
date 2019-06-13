public class Person {
  private String age;
  private String name;

  public String getage() {
    return age;
  }

  public void setage(String age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public static void main(String[] args) {
	    Person model = new Person();
	    model.setName("Tom");
	    model.setage("1");
	    PersonView view = new PersonView();

	    PersonController controller = new PersonController(model, view);
	    controller.updateView();
	    controller.setPersonName("New Name");
	    controller.updateView();
	  }
}

class PersonView {
  public void printPersonDetails(String name, String age) {
    System.out.println("Person: ");
    System.out.println("Name: " + name);
    System.out.println("age: " + age);
  }
}

class PersonController {
  private Person model;
  private PersonView view;

  public PersonController(Person model, PersonView view) {
    this.model = model;
    this.view = view;
  }

  public void setPersonName(String name) {
    model.setName(name);
  }

  public String getPersonName() {
    return model.getName();
  }

  public void setPersonage(String rollNo) {
    model.setage(rollNo);
  }

  public String getPersonage() {
    return model.getage();
  }

  public void updateView() {
    view.printPersonDetails(model.getName(), model.getage());
  }
}
