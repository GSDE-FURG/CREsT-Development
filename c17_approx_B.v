module c17_approx_B (
    IN_a, IN_b, IN_c, IN_d, IN_e, OUT_a, OUT_b );
  input  IN_a, IN_b, IN_c, IN_d, IN_e;
  output OUT_a, OUT_b ;
  wire W1, W2, W3, W4;
  
  NAND2   G0(.A(IN_b), .B(IN_c), .Y(W1));
  NAND2   G2(.A(IN_a), .B(W1), .Y(W4));  
  INV     G1(.A(IN_e), .Y(W2));
  NAND2   G3(.A(W1), .B(IN_d), .Y(W3));
  NAND2   G4(.A(W4), .B(W3), .Y(OUT_a));
  NAND2   G5(.A(W3), .B(W2), .Y(OUT_b));

endmodule