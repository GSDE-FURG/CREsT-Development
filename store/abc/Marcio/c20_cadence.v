// Benchmark "C17.blif" written by ABC on Thu Jun 15 02:23:22 2017

module C20.cadence (
    G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat, G8gat, G9gat, G10gat,
    G11gat, G12gat, G13gat, G14gat, G15gat, G16gat, G17gat );
  input  G1gat, G2gat, G3gat, G4gat, G5gat, G6gat, G7gat, G8gat, G9gat, G10gat,
    G11gat, G12gat, G13gat, G14gat, G15gat, G16gat;
  output G17gat ;
  wire w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12,
	w13, w14, w15, w16, w17, w18, w19;

  AND2X1   G000(.A(G1gat), .B(G2gat), .Y(w1));
  OR2X1    G001(.A(G4gat), .B(G5gat), .Y(w2));
  NAND2X1  G005(.A(G6gat), .B(G7gat), .Y(w3));
  AND2X1   G002(.A(G8gat), .B(G9gat), .Y(w4));
  OR2X1    G007(.A(G10gat), .B(G11gat), .Y(w5));
  AND2X1   G008(.A(G12gat), .B(G13gat), .Y(w6));
  NAND2X1  G003(.A(G14gat), .B(G15gat), .Y(w7));
  INVX1    G009(.A(G16gat), .Y(w8));

  OR2X1    G004(.A(G3gat), .B(w2), .Y(w9));
  INVX1    G006(.A(w4), .Y(w10));
  NAND3X1  G010(.A(w5), .B(w6), .C(w7), .Y(w11));

  AND2X1   G012(.A(w1), .B(w9), .Y(w12));
  OR2X1    G011(.A(w3), .B(w10), .Y(w13));
  INVX1    G014(.A(w11), .Y(w14));

  INVX1    G013(.A(w13), .Y(w15));
  XOR2X1   G016(.A(w13), .B(w14), .Y(w16));
  NOR3X1   G018(.A(w14), .B(w13), .C(w8), .Y(w17));

  AND2X1   G015(.A(w12), .B(w15), .Y(w18));

  NAND2X1  G017(.A(w18), .B(w16), .Y(w19));

  OR3X1    G019(.A(w19), .B(w16), .C(w17), .Y(G17gat));

endmodule


