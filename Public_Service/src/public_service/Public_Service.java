package public_service;

import java.util.Random;

public final class Public_Service {
  static Random rand = new Random();                                     /* A static instance of random class that can be used in multiple classes */
  
  public static void main(String[] args) {
    /* Get the arguments from command line as parameters */
    final int N = Integer.parseInt(args[0]), Nf = Integer.parseInt(args[1]), Ng = Integer.parseInt(args[2]), No = Integer.parseInt(args[3]), Nl = Integer.parseInt(args[4]), K = Integer.parseInt(args[5]), L = Integer.parseInt(args[6]);
   Building public_service = new Building(N, Nf, Ng, No, Nl);                                  /* Create a building according to these parameters */
    Visitor[] visitors = new Visitor[K];                                                                         /* Allocate memory for K visitors */
    Visitor_List waiting_list = new Visitor_List();                                  /* Waiting list for visitors that couldn't enter the building */
    for (int visitor_counter = 1; visitor_counter <= K; visitor_counter++) {
      visitors[visitor_counter - 1] = new Visitor();                                           /* Initialize each visitor with random destinations */
      if (!public_service.Enter(visitors[visitor_counter - 1])) {                                   /* Each visitor attempts to enter the building */
        waiting_list.Add_Visitor_To_The_End(visitors[visitor_counter - 1]);       /* Add visitors that couldn't enter the building to waiting list */
      }
    }
    for (int lift_cycles = 1; lift_cycles <= L; lift_cycles++) {                                      /* Execute as many lift cycles as determined */
      public_service.Serve_Visitors();                                      /* Execute a lift cycle, serve visitors and release space for new ones */
      int size_of_waiting_list = waiting_list.Size();                                               /* Count how many visitors are in waiting list */
      if (size_of_waiting_list == 0)                                   /* If waiting list is empty, then no more visitors won't enter the building */
        continue;                                                                                                     /* Go to the next lift cycle */
      for (int visitor_counter = 1, visitor_position = 1; visitor_counter <= size_of_waiting_list; visitor_counter++) {
        if(public_service.Enter(waiting_list.Get_ith_Visitor(visitor_position))) {  /* Each visitor in waiting list attempts to enter the building */
          waiting_list.Exclude_This_Visitor(waiting_list.Get_ith_Visitor(visitor_position));  /* If he can enter, then erase him from waiting list */
                            /* In this case don't increase the position, because the next visitor will appear at the same position of waiting list */
        }
        else
          visitor_position++;                                   /* If he can't enter, then increase the position in order to find the next visitor */
      }
    }
  }
}