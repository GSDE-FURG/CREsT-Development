module Inv_Paralelo (
    entrada, OUT );
  input  entrada;
  output OUT1, OUT2 ;
  wire w1;
  
  INVX1   U0(.A(entrada), .Y(OUT1));
  INVX1   U2(.A(entrada), .Y(OUT2));  
  

endmodule


