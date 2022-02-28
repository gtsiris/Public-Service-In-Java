package public_service;

public class Person {  /* Disclaimer: In other context, person might has different fields */
  private final int dest_floor;                     /* The floor that he would like to go */
  private final int dest_office;                   /* The office that he would like to go */
  
  public Person(final int destination_floor, final int destination_office) {
    dest_floor = destination_floor;      /* Initialize the floor that he would like to go */
    dest_office = destination_office;   /* Initialize the office that he would like to go */
  }

  public final int Get_dest_floor() {          /* Find at which floor he would like to go */
    return dest_floor;                  /* Return at which floor visitor would like to go */
  }

  public final int Get_dest_office() {        /* Find at which office he would like to go */
    return dest_office;                /* Return at which office visitor would like to go */
  }
}
