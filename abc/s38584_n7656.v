//Converted to Combinational (Partial output: n7656) , Module name: s38584_n7656
module s38584_n7656 ( g35, g4821, g4826, g4646, g4674, g4688, g4681, g128, g4831, g4776, g4801, g4793, g4732, g4722, g4727, g4717, g4765, g4698, g4785, g4709, g4737, g4754, g4743, n7656 );
input g35, g4821, g4826, g4646, g4674, g4688, g4681, g128, g4831, g4776, g4801, g4793, g4732, g4722, g4727, g4717, g4765, g4698, g4785, g4709, g4737, g4754, g4743;
output n7656;
wire n4620, n7425, n7443, n7424, n7421, n7422_1, n7423, n7442_1, n7420, n7014, n4967, n7441, n7426, n7432_1, n7440, n7427_1, n7435, n4721, n7431, n7030, n7439, n4822_1, n7434, n4969_1, n7433, n4720, n7429, n7430, n4970, n7438, n4821, n4813, n4816, n4819, n7428, n4812_1, n4820, n6720, n7437_1, n7436, n4814, n4815, n4817_1, n4818;
AOI21X1  g2799(.A0(n7443), .A1(n7425), .B0(n4620), .Y(n7656));
INVX1    g0000(.A(g35), .Y(n4620));
OAI21X1  g2780(.A0(n7422_1), .A1(n7421), .B0(n7424), .Y(n7425));
NAND2X1  g2798(.A(n7442_1), .B(n7423), .Y(n7443));
INVX1    g2779(.A(n7423), .Y(n7424));
INVX1    g2776(.A(n7420), .Y(n7421));
OAI22X1  g2777(.A0(n4967), .A1(g4826), .B0(n7014), .B1(g4821), .Y(n7422_1));
NOR4X1   g2778(.A(g4681), .B(g4688), .C(g4674), .D(g4646), .Y(n7423));
OAI21X1  g2797(.A0(n7432_1), .A1(n7426), .B0(n7441), .Y(n7442_1));
AOI22X1  g2775(.A0(g4681), .A1(g4831), .B0(g128), .B1(g4646), .Y(n7420));
INVX1    g2370(.A(g4674), .Y(n7014));
INVX1    g0347(.A(g4688), .Y(n4967));
AOI21X1  g2796(.A0(n7435), .A1(n7427_1), .B0(n7440), .Y(n7441));
INVX1    g2781(.A(n4721), .Y(n7426));
XOR2X1   g2787(.A(n7431), .B(n7427_1), .Y(n7432_1));
NAND2X1  g2795(.A(n7439), .B(n7030), .Y(n7440));
INVX1    g2782(.A(n4822_1), .Y(n7427_1));
OAI22X1  g2790(.A0(n7433), .A1(g4776), .B0(n4969_1), .B1(n7434), .Y(n7435));
NOR3X1   g0101(.A(g4793), .B(g4801), .C(n4720), .Y(n4721));
AND2X1   g2786(.A(n7430), .B(n7429), .Y(n7431));
INVX1    g2386(.A(n4970), .Y(n7030));
OR4X1    g2794(.A(g4793), .B(g4801), .C(g4776), .D(n7438), .Y(n7439));
NOR4X1   g0202(.A(n4819), .B(n4816), .C(n4813), .D(n4821), .Y(n4822_1));
OR2X1    g2789(.A(g4801), .B(g4776), .Y(n7434));
INVX1    g0349(.A(g4793), .Y(n4969_1));
NAND2X1  g2788(.A(g4793), .B(g4801), .Y(n7433));
INVX1    g0100(.A(g4776), .Y(n4720));
AOI22X1  g2784(.A0(n4812_1), .A1(g4722), .B0(g4732), .B1(n7428), .Y(n7429));
AOI22X1  g2785(.A0(n6720), .A1(g4717), .B0(g4727), .B1(n4820), .Y(n7430));
NOR3X1   g0350(.A(n4969_1), .B(g4801), .C(n4720), .Y(n4970));
AOI21X1  g2793(.A0(n4820), .A1(n7436), .B0(n7437_1), .Y(n7438));
AND2X1   g0201(.A(n4820), .B(g4765), .Y(n4821));
AND2X1   g0193(.A(n4812_1), .B(g4698), .Y(n4813));
NOR3X1   g0196(.A(g4785), .B(n4815), .C(n4814), .Y(n4816));
NOR3X1   g0199(.A(n4818), .B(g4709), .C(n4817_1), .Y(n4819));
NOR2X1   g2783(.A(g4785), .B(n4815), .Y(n7428));
NOR2X1   g0192(.A(g4785), .B(g4709), .Y(n4812_1));
AND2X1   g0200(.A(g4785), .B(g4709), .Y(n4820));
NOR2X1   g2079(.A(n4818), .B(g4709), .Y(n6720));
XOR2X1   g2792(.A(g4785), .B(g4709), .Y(n7437_1));
INVX1    g2791(.A(g4737), .Y(n7436));
INVX1    g0194(.A(g4754), .Y(n4814));
INVX1    g0195(.A(g4709), .Y(n4815));
INVX1    g0197(.A(g4743), .Y(n4817_1));
INVX1    g0198(.A(g4785), .Y(n4818));

endmodule
