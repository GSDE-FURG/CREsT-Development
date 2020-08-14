module FullAdderV2 (
    G1gat, G2gat, G3gat, G4gat, G5gat );
  input  G1gat, G2gat, G3gat;
  output G4gat, G5gat ;
  wire w1, w2, w3, w4, w5, w6, w7;

  NAND2X1   G1(.A(G1gat), .B(G2gat), .Y(w1));
  NAND2X1   G2(.A(G1gat), .B(w1), .Y(w2));
  NAND2X1   G3(.A(w1), .B(G2gat), .Y(w3));
  NAND2X1   G4(.A(w2), .B(w3), .Y(w4));
  NAND2X1   G5(.A(w4), .B(G3gat), .Y(w5));
  NAND2X1   G6(.A(w4), .B(w5), .Y(w6));
  NAND2X1   G7(.A(w5), .B(G3gat), .Y(w7));
  NAND2X1   G8(.A(w5), .B(w1), .Y(G5gat));
  NAND2X1   G9(.A(w6), .B(w7), .Y(G4gat));

endmodule


