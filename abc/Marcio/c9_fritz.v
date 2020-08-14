module C9Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat );
  input  G1gat, G2gat, G3gat, G4gat;
  output G5gat ;
  wire w1, w2, w3, w4, w5, w6, w7, w8;

  INVX1     G000(.A(G2gat), .Y(w1));
  OR2X1     G001(.A(G3gat), .B(G4gat), .Y(w2));
 
  NAND2X1   G002(.A(G1gat), .B(w1), .Y(w3));
  AND2X1    G003(.A(w1), .B(G3gat), .Y(w4));
  OR2X1     G004(.A(w2), .B(G4gat), .Y(w5));

  AND3X1    G005(.A(w3), .B(w4), .C(w2), .Y(w6));
  AND2X1    G006(.A(G3gat), .B(w5), .Y(w7));
  INVX1     G007(.A(G3gat), .Y(w8));

  OR3X1     G008(.A(w6), .B(w7), .C(w8), .Y(G5gat));

endmodule


