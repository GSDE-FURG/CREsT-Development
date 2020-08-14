//Converted to Combinational (Partial output: n240) , Module name: s5378_n240
module s5378_n240 ( n722gat, n726gat, n842gat, n271gat, n160gat, n337gat, n394gat, n703gat, n341gat, n240 );
input n722gat, n726gat, n842gat, n271gat, n160gat, n337gat, n394gat, n703gat, n341gat;
output n240;
wire n776, n772, n774_1, n775, n773, n762, n770, n753, n771, n761, n769_1, n765, n767, n768, n752, n749_1, n750, n751, n760, n756, n758, n759_1, n763, n764_1, n766, n711, n748, n744_1, n754_1, n755, n757;
NAND4X1  g156(.A(n775), .B(n774_1), .C(n772), .D(n776), .Y(n240));
NAND3X1  g155(.A(n770), .B(n762), .C(n773), .Y(n776));
NAND3X1  g151(.A(n771), .B(n762), .C(n753), .Y(n772));
NAND3X1  g153(.A(n771), .B(n761), .C(n773), .Y(n774_1));
NAND3X1  g154(.A(n770), .B(n761), .C(n753), .Y(n775));
INVX1    g152(.A(n753), .Y(n773));
INVX1    g141(.A(n761), .Y(n762));
NOR4X1   g149(.A(n768), .B(n767), .C(n765), .D(n769_1), .Y(n770));
NOR4X1   g132(.A(n751), .B(n750), .C(n749_1), .D(n752), .Y(n753));
INVX1    g150(.A(n770), .Y(n771));
NOR4X1   g140(.A(n759_1), .B(n758), .C(n756), .D(n760), .Y(n761));
NOR3X1   g148(.A(n842gat), .B(n726gat), .C(n722gat), .Y(n769_1));
NOR3X1   g144(.A(n764_1), .B(n763), .C(n722gat), .Y(n765));
NOR3X1   g146(.A(n764_1), .B(n726gat), .C(n766), .Y(n767));
NOR3X1   g147(.A(n842gat), .B(n763), .C(n766), .Y(n768));
NOR3X1   g131(.A(n337gat), .B(n160gat), .C(n271gat), .Y(n752));
NOR3X1   g128(.A(n337gat), .B(n748), .C(n711), .Y(n749_1));
NOR3X1   g129(.A(n744_1), .B(n748), .C(n271gat), .Y(n750));
NOR3X1   g130(.A(n744_1), .B(n160gat), .C(n711), .Y(n751));
NOR3X1   g139(.A(n341gat), .B(n703gat), .C(n394gat), .Y(n760));
NOR3X1   g135(.A(n755), .B(n754_1), .C(n394gat), .Y(n756));
NOR3X1   g137(.A(n341gat), .B(n754_1), .C(n757), .Y(n758));
NOR3X1   g138(.A(n755), .B(n703gat), .C(n757), .Y(n759_1));
INVX1    g142(.A(n726gat), .Y(n763));
INVX1    g143(.A(n842gat), .Y(n764_1));
INVX1    g145(.A(n722gat), .Y(n766));
INVX1    g090(.A(n271gat), .Y(n711));
INVX1    g127(.A(n160gat), .Y(n748));
INVX1    g123(.A(n337gat), .Y(n744_1));
INVX1    g133(.A(n703gat), .Y(n754_1));
INVX1    g134(.A(n341gat), .Y(n755));
INVX1    g136(.A(n394gat), .Y(n757));

endmodule
