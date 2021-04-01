module NAND2x1 (G1gat, G2gat);
  input  g1, g2;
  output out;
  
  NAND2X1   U0(.A(g1), .B(g2), .Y(out));
  

endmodule


