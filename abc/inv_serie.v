module Inv_Serie (
    entrada, OUT );
  input  entrada;
  output OUT ;
  wire w1;
  
  INVX1   U0(.A(entrada), .Y(w1));
  INVX1   U2(.A(w1), .Y(OUT));  
  

endmodule


