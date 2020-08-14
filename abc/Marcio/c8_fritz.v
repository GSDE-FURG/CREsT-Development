module C8Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat;
  output G5gat, G6gat, G7gat ;
  wire w1, w2, w3, w4, w5;

  
  NAND2X1   G000(.A(G1gat), .B(G2gat), .Y(w1));
  NOR2X1    G001(.A(G2gat), .B(G3gat), .Y(w2));
  AND2X1    G002(.A(G3gat), .B(G4gat), .Y(w3));

  AND2X1    G003(.A(w1), .B(w2), .Y(w4));
  OR2X1     G004(.A(w2), .B(w3), .Y(w5));

  OR2X1     G005(.A(G1gat), .B(w4), .Y(G5gat));
  AND2X1    G006(.A(w4), .B(w5), .Y(G6gat));
  NOR2X1    G007(.A(w5), .B(G4gat), .Y(G7gat));

endmodule


