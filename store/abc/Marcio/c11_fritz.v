module C11Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat, G8gat,
    G9gat, G10gat, G11gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat, G8gat,
    G9gat, G10gat;
  output G11gat ;
  wire w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;

  AND2X1    G000(.A(G2gat), .B(G3gat), .Y(w1));
  NAND2X1   G001(.A(G4gat), .B(G5gat), .Y(w2));
  XOR2X1    G002(.A(G6gat), .B(G7gat), .Y(w3));

  NAND2X1   G003(.A(G1gat), .B(w1), .Y(w4));
  NAND2X1   G004(.A(w2), .B(w3), .Y(w5));
  INVX1     G005(.A(w3), .Y(w6));

  NOR3X1    G006(.A(w4), .B(w5), .C(w6), .Y(w7));
  AND2X1    G007(.A(G8gat), .B(G9gat), .Y(w8));
  AND2X1    G008(.A(G9gat), .B(G10gat), .Y(w9));
 
  NOR3X1    G009(.A(w7), .B(w8), .C(w9), .Y(w10));

  AND2X1    G010(.A(w10), .B(G10gat), .Y(G11gat));

endmodule


