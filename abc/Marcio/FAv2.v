module FullAdderV2Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat );
  input  G1gat, G2gat, G3gat;
  output G4gat, G5gat ;
  wire w1, w2, w3, w4, w5, w6, w7;

  NAND2X1   G000(.A(G1gat), .B(G2gat), .Y(w1));

  NAND2X1   G001(.A(G1gat), .B(w1), .Y(w2));
  NAND2X1   G002(.A(w1), .B(G2gat), .Y(w3));

  NAND2X1   G003(.A(w2), .B(w3), .Y(w4));

  NAND2X1   G004(.A(w4), .B(G3gat), .Y(w5));

  NAND2X1   G005(.A(w4), .B(w5), .Y(w6));
  NAND2X1   G006(.A(w5), .B(G3gat), .Y(w7));
  NAND2X1   G007(.A(w5), .B(w1), .Y(G5gat));

  NAND2X1   G008(.A(w6), .B(w7), .Y(G4gat));

endmodule


