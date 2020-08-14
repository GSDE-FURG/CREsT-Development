module C17V3_Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat;
  output G6gat, G7gat ;
  wire w1, w2, w3, w4;

  
  NAND2X1   G000(.A(G5gat), .B(G2gat), .Y(w1));
  NAND2X1   G001(.A(G1gat), .B(G5gat), .Y(w2));
  
  NAND2X1   G002(.A(G4gat), .B(w1), .Y(w3));
  NAND2X1   G003(.A(w1), .B(G3gat), .Y(w4));

  NAND2X1   G004(.A(w2), .B(w3), .Y(G6gat));
  NAND2X1   G005(.A(w3), .B(w4), .Y(G7gat));

endmodule


