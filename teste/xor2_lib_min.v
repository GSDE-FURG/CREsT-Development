// Benchmark "xor2.blif" written by ABC on Wed Mar 17 05:23:53 2021

module \xor2.blif  ( 
    A, B,
    Y  );
  input  A, B;
  output Y;
  wire new_n4_, new_n5_, new_n6_, new_n7_;
  INV   g0(.A(A), .Y(new_n4_));
  NAND2 g1(.A(B), .B(new_n4_), .Y(new_n5_));
  INV   g2(.A(B), .Y(new_n6_));
  NAND2 g3(.A(new_n6_), .B(A), .Y(new_n7_));
  NAND2 g4(.A(new_n7_), .B(new_n5_), .Y(Y));
endmodule


