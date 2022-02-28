package public_service;

public final class Guest extends Person {
  final Visitor visitor;                                                                       /* Guest accompanies a visitor */
  
  public Guest(final Visitor visitor_to_accompany) {
    super(visitor_to_accompany.Get_dest_floor(), visitor_to_accompany.Get_dest_office());  /* Initialize his nature as person */
    visitor = visitor_to_accompany;                                       /* Keep a reference to the visitor that accompanies */
  }
    
  public final Visitor Get_visitor() {                                        /* Find the visitor that this guest accompanies */
    return visitor;                                                         /* Return the visitor that this guest accompanies */
  }
}
