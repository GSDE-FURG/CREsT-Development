//Converted to Combinational (Partial output: n4368) , Module name: s38584_n4368 , Timestamp: 2018-12-03T15:51:17.183487 
module s38584_n4368 ( g35, g283, g287, g278, g691, g255, g718, g753, g655, g246, g239, g225, g232, g807, g554, g370, g518, g499, g490, g528, g482, g262, g269, g385, g358, g376, n4368 );
input g35, g283, g287, g278, g691, g255, g718, g753, g655, g246, g239, g225, g232, g807, g554, g370, g518, g499, g490, g528, g482, g262, g269, g385, g358, g376;
output n4368;
wire n8851, n6114_1, n8850, n6115, n6113, n6104, n5516_1, n5661, n6112, n6110_1, n5660_1, n5655_1, n5657, n5658, n6111, n6105_1, n6106, n6109, n5659, n5347, n5320, n5601, n5656, n5132, n5142, n5130_1, n6108, n6107, n5600, n5598, n5599;
MX2X1    g4203(.A(g283), .B(n8851), .S0(g35), .Y(n4368));
MX2X1    g4202(.A(n6115), .B(n8850), .S0(n6114_1), .Y(n8851));
OR4X1    g1474(.A(n5661), .B(n5516_1), .C(n6104), .D(n6113), .Y(n6114_1));
NOR4X1   g4201(.A(n5661), .B(n5516_1), .C(n6115), .D(n6113), .Y(n8850));
INVX1    g1475(.A(g287), .Y(n6115));
MX2X1    g1473(.A(n6110_1), .B(n6112), .S0(g278), .Y(n6113));
INVX1    g1464(.A(g283), .Y(n6104));
INVX1    g0876(.A(g691), .Y(n5516_1));
NOR4X1   g1021(.A(n5658), .B(n5657), .C(n5655_1), .D(n5660_1), .Y(n5661));
OR4X1    g1472(.A(g255), .B(n6106), .C(n6105_1), .D(n6111), .Y(n6112));
NAND4X1  g1470(.A(g255), .B(n6106), .C(n6105_1), .D(n6109), .Y(n6110_1));
OAI21X1  g1020(.A0(n5320), .A1(n5347), .B0(n5659), .Y(n5660_1));
INVX1    g1015(.A(n5601), .Y(n5655_1));
NAND4X1  g1017(.A(n5130_1), .B(n5142), .C(n5132), .D(n5656), .Y(n5657));
NOR3X1   g1018(.A(g655), .B(g753), .C(g718), .Y(n5658));
NAND4X1  g1471(.A(n6107), .B(g239), .C(g246), .D(n6108), .Y(n6111));
INVX1    g1465(.A(g225), .Y(n6105_1));
INVX1    g1466(.A(g232), .Y(n6106));
NOR4X1   g1469(.A(n6107), .B(g239), .C(g246), .D(n6108), .Y(n6109));
NAND3X1  g1019(.A(g655), .B(g753), .C(g718), .Y(n5659));
INVX1    g0707(.A(g807), .Y(n5347));
INVX1    g0680(.A(g554), .Y(n5320));
NOR4X1   g0961(.A(n5599), .B(g370), .C(n5598), .D(n5600), .Y(n5601));
NOR2X1   g1016(.A(g499), .B(g518), .Y(n5656));
INVX1    g0500(.A(g490), .Y(n5132));
INVX1    g0510(.A(g528), .Y(n5142));
INVX1    g0498(.A(g482), .Y(n5130_1));
INVX1    g1468(.A(g262), .Y(n6108));
INVX1    g1467(.A(g269), .Y(n6107));
INVX1    g0960(.A(g385), .Y(n5600));
INVX1    g0958(.A(g358), .Y(n5598));
INVX1    g0959(.A(g376), .Y(n5599));

endmodule
