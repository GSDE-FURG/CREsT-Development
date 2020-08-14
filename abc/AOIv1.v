// Benchmark "C17.blif" written by ABC on Sat Apr 29 20:12:48 2017

module C17.blif ( 
    A, B, C, OUT );
  input  A, B, C;
  output OUT ;
  AOI21X1 g0(.A0(B), .A1(C), .B0(A),.Y(OUT)); 
endmodule


