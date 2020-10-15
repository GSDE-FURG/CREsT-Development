module C17G6gat (
    G2gat, G3gat, G4gat, G5gat, G7gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat;
  output G7gat;
  wire w1, w3, w4;
  
  NAND2X1   U0(.A(G5gat), .B(G2gat), .Y(w1)); 
  NAND2X1   U1(.A(w1), .B(G4gat), .Y(w3));
  NAND2X1   U4(.A(w1), .B(G3gat), .Y(w4));
  NAND2X1   U5(.A(w4), .B(w3), .Y(G7gat));

endmodule


