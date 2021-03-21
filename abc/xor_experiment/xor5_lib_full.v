// Benchmark "xor5.blif" written by ABC on Thu Mar 18 05:28:41 2021

module \xor5.blif  ( 
    A, B, C, D, E,
    Y  );
  input  A, B, C, D, E;
  output Y;
  wire new_n7_, new_n8_, new_n9_, new_n10_, new_n11_, new_n12_, new_n13_,
    new_n14_, new_n15_, new_n16_;
  XOR2   g00(.A(E), .B(D), .Y(new_n7_));
  INV    g01(.A(A), .Y(new_n8_));
  XOR2   g02(.A(C), .B(B), .Y(new_n9_));
  NOR2   g03(.A(new_n9_), .B(new_n8_), .Y(new_n10_));
  XOR2   g04(.A(C), .B(B), .Y(new_n11_));
  AOI21  g05(.A0(new_n11_), .A1(new_n8_), .B0(new_n10_), .Y(new_n12_));
  INV    g06(.A(D), .Y(new_n13_));
  XOR2   g07(.A(E), .B(new_n13_), .Y(new_n14_));
  NOR2   g08(.A(new_n9_), .B(A), .Y(new_n15_));
  AOI21  g09(.A0(new_n11_), .A1(A), .B0(new_n15_), .Y(new_n16_));
  OAI22  g10(.A0(new_n16_), .A1(new_n14_), .B0(new_n12_), .B1(new_n7_), .Y(Y));
endmodule


