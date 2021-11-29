module C17V3 (
    G1gat, G2gat, G6gat);
  input  G1gat, G2gat;
  output G6gat ;
  wire w1, w2, w3, w4;
  
  NAND2X1   U0(.A(G1gat), .B(G2gat), .Y(G6gat));

endmodule


