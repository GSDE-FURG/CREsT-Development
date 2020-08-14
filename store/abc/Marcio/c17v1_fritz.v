module C17V1_Fritz (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat;
  output G6gat, G7gat ;
  wire w1, w2, w3, w4, w5, w6, w7;

  
  NAND2X1   U0(.A(G5gat), .B(G1gat), .Y(w1));
  NAND2X1   U1(.A(G5gat), .B(G2gat), .Y(w2));
  INVX1     U4(.A(G2gat), .Y(w3));

  NAND2X1   U2(.A(w2), .B(G4gat), .Y(w4));
  INVX1     U5(.A(G5gat), .Y(w5));

  NOR2X1    U7(.A(G4gat), .B(G3gat), .Y(w6));
  NOR2X1    U6(.A(w5), .B(w3), .Y(w7));
  
  NAND2X1   U3(.A(w4), .B(w1), .Y(G6gat));
  NOR2X1    U8(.A(w6), .B(w7), .Y(G7gat));

endmodule


