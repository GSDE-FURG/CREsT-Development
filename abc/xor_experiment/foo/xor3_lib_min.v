// Benchmark "xor3.blif" written by ABC on Thu Mar 18 03:44:08 2021

module \xor3.blif  ( 
    A, B, C,
    Y  );
  input  A, B, C;
  output Y;
  wire new_n5_, new_n6_, new_n7_, new_n8_, new_n9_, new_n10_, new_n11_,
    new_n12_, new_n13_, new_n14_, new_n15_;
  NAND2 g00(.A(C), .B(B), .Y(new_n5_));
  INV   g01(.A(B), .Y(new_n6_));
  INV   g02(.A(C), .Y(new_n7_));
  NAND2 g03(.A(new_n7_), .B(new_n6_), .Y(new_n8_));
  NAND2 g04(.A(new_n8_), .B(new_n5_), .Y(new_n9_));
  NAND2 g05(.A(new_n9_), .B(A), .Y(new_n10_));
  INV   g06(.A(A), .Y(new_n11_));
  NAND2 g07(.A(C), .B(new_n6_), .Y(new_n12_));
  NAND2 g08(.A(new_n7_), .B(B), .Y(new_n13_));
  NAND2 g09(.A(new_n13_), .B(new_n12_), .Y(new_n14_));
  NAND2 g10(.A(new_n14_), .B(new_n11_), .Y(new_n15_));
  NAND2 g11(.A(new_n15_), .B(new_n10_), .Y(Y));
endmodule


