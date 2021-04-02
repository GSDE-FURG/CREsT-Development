// Benchmark "c880.blif" written by ABC on Wed Mar 31 18:38:50 2021

module \c880.blif  ( 
    G1gat, G8gat, G13gat, G17gat, G26gat, G29gat, G36gat, G42gat, G51gat,
    G55gat, G59gat, G68gat, G72gat, G73gat, G74gat, G75gat, G80gat, G85gat,
    G86gat, G87gat, G88gat, G89gat, G90gat, G91gat, G96gat, G101gat,
    G106gat, G111gat, G116gat, G121gat, G126gat, G130gat, G135gat, G138gat,
    G143gat, G146gat, G149gat, G152gat, G153gat, G156gat, G159gat, G165gat,
    G171gat, G177gat, G183gat, G189gat, G195gat, G201gat, G207gat, G210gat,
    G219gat, G228gat, G237gat, G246gat, G255gat, G259gat, G260gat, G261gat,
    G267gat, G268gat,
    G388gat, G389gat, G390gat, G391gat, G418gat, G419gat, G420gat, G421gat,
    G422gat, G423gat, G446gat, G447gat, G448gat, G449gat, G450gat, G767gat,
    G768gat, G850gat, G863gat, G864gat, G865gat, G866gat, G874gat, G878gat,
    G879gat, G880gat  );
  input  G1gat, G8gat, G13gat, G17gat, G26gat, G29gat, G36gat, G42gat,
    G51gat, G55gat, G59gat, G68gat, G72gat, G73gat, G74gat, G75gat, G80gat,
    G85gat, G86gat, G87gat, G88gat, G89gat, G90gat, G91gat, G96gat,
    G101gat, G106gat, G111gat, G116gat, G121gat, G126gat, G130gat, G135gat,
    G138gat, G143gat, G146gat, G149gat, G152gat, G153gat, G156gat, G159gat,
    G165gat, G171gat, G177gat, G183gat, G189gat, G195gat, G201gat, G207gat,
    G210gat, G219gat, G228gat, G237gat, G246gat, G255gat, G259gat, G260gat,
    G261gat, G267gat, G268gat;
  output G388gat, G389gat, G390gat, G391gat, G418gat, G419gat, G420gat,
    G421gat, G422gat, G423gat, G446gat, G447gat, G448gat, G449gat, G450gat,
    G767gat, G768gat, G850gat, G863gat, G864gat, G865gat, G866gat, G874gat,
    G878gat, G879gat, G880gat;
  wire new_n87_, new_n88_, new_n89_, new_n91_, new_n92_, new_n95_, new_n96_,
    new_n98_, new_n99_, new_n100_, new_n101_, new_n103_, new_n104_,
    new_n109_, new_n110_, new_n111_, new_n114_, new_n116_, new_n117_,
    new_n118_, new_n119_, new_n121_, new_n123_, new_n125_, new_n126_,
    new_n127_, new_n128_, new_n129_, new_n130_, new_n131_, new_n132_,
    new_n133_, new_n134_, new_n135_, new_n136_, new_n137_, new_n138_,
    new_n139_, new_n140_, new_n141_, new_n142_, new_n143_, new_n144_,
    new_n145_, new_n146_, new_n147_, new_n148_, new_n149_, new_n150_,
    new_n151_, new_n152_, new_n153_, new_n154_, new_n155_, new_n156_,
    new_n158_, new_n159_, new_n160_, new_n161_, new_n162_, new_n163_,
    new_n164_, new_n165_, new_n166_, new_n167_, new_n168_, new_n169_,
    new_n170_, new_n171_, new_n172_, new_n173_, new_n174_, new_n175_,
    new_n176_, new_n177_, new_n178_, new_n179_, new_n180_, new_n181_,
    new_n182_, new_n183_, new_n184_, new_n185_, new_n186_, new_n187_,
    new_n188_, new_n190_, new_n191_, new_n192_, new_n193_, new_n194_,
    new_n195_, new_n196_, new_n197_, new_n198_, new_n199_, new_n200_,
    new_n201_, new_n202_, new_n203_, new_n204_, new_n205_, new_n206_,
    new_n207_, new_n208_, new_n209_, new_n210_, new_n211_, new_n212_,
    new_n213_, new_n214_, new_n215_, new_n216_, new_n217_, new_n218_,
    new_n220_, new_n221_, new_n222_, new_n223_, new_n224_, new_n225_,
    new_n226_, new_n227_, new_n228_, new_n229_, new_n230_, new_n231_,
    new_n232_, new_n233_, new_n234_, new_n235_, new_n236_, new_n237_,
    new_n238_, new_n239_, new_n240_, new_n241_, new_n242_, new_n243_,
    new_n244_, new_n245_, new_n246_, new_n247_, new_n248_, new_n249_,
    new_n250_, new_n251_, new_n252_, new_n253_, new_n254_, new_n255_,
    new_n256_, new_n257_, new_n258_, new_n260_, new_n261_, new_n262_,
    new_n263_, new_n264_, new_n265_, new_n266_, new_n267_, new_n268_,
    new_n269_, new_n270_, new_n271_, new_n272_, new_n273_, new_n274_,
    new_n276_, new_n277_, new_n278_, new_n279_, new_n280_, new_n281_,
    new_n282_, new_n283_, new_n285_, new_n286_, new_n287_, new_n288_,
    new_n289_, new_n290_, new_n291_, new_n292_, new_n293_, new_n294_,
    new_n295_, new_n296_, new_n297_, new_n298_, new_n299_, new_n300_,
    new_n301_, new_n302_, new_n303_, new_n304_, new_n305_, new_n306_,
    new_n307_, new_n308_, new_n309_, new_n310_, new_n311_, new_n312_,
    new_n313_, new_n314_, new_n315_, new_n316_, new_n317_, new_n318_,
    new_n320_, new_n321_, new_n322_, new_n323_, new_n324_, new_n325_,
    new_n326_, new_n327_, new_n328_, new_n329_, new_n330_, new_n332_,
    new_n333_, new_n334_, new_n335_, new_n336_, new_n337_, new_n338_,
    new_n340_, new_n341_, new_n342_, new_n343_, new_n344_, new_n345_,
    new_n346_, new_n347_, new_n348_, new_n349_, new_n350_, new_n351_,
    new_n352_, new_n354_, new_n355_, new_n356_, new_n357_, new_n358_,
    new_n359_, new_n360_, new_n361_, new_n362_;
  INV    g000(.A(G29gat), .Y(new_n87_));
  INV    g001(.A(G42gat), .Y(new_n88_));
  INV    g002(.A(G75gat), .Y(new_n89_));
  NOR3   g003(.A(new_n89_), .B(new_n88_), .C(new_n87_), .Y(G388gat));
  INV    g004(.A(G80gat), .Y(new_n91_));
  NAND2  g005(.A(G36gat), .B(G29gat), .Y(new_n92_));
  NOR2   g006(.A(new_n92_), .B(new_n91_), .Y(G389gat));
  NOR2   g007(.A(new_n92_), .B(new_n88_), .Y(G390gat));
  INV    g008(.A(G85gat), .Y(new_n95_));
  INV    g009(.A(G86gat), .Y(new_n96_));
  NOR2   g010(.A(new_n96_), .B(new_n95_), .Y(G391gat));
  INV    g011(.A(G1gat), .Y(new_n98_));
  INV    g012(.A(G8gat), .Y(new_n99_));
  INV    g013(.A(G13gat), .Y(new_n100_));
  INV    g014(.A(G17gat), .Y(new_n101_));
  NOR4   g015(.A(new_n101_), .B(new_n100_), .C(new_n99_), .D(new_n98_), .Y(G418gat));
  INV    g016(.A(G26gat), .Y(new_n103_));
  NOR4   g017(.A(new_n103_), .B(new_n101_), .C(new_n100_), .D(new_n98_), .Y(new_n104_));
  OAI21  g018(.A0(new_n92_), .A1(new_n88_), .B0(new_n104_), .Y(G419gat));
  NAND3  g019(.A(G80gat), .B(G75gat), .C(G59gat), .Y(G420gat));
  NAND3  g020(.A(G80gat), .B(G59gat), .C(G36gat), .Y(G421gat));
  NAND3  g021(.A(G59gat), .B(G42gat), .C(G36gat), .Y(G422gat));
  INV    g022(.A(G87gat), .Y(new_n109_));
  INV    g023(.A(G88gat), .Y(new_n110_));
  INV    g024(.A(G90gat), .Y(new_n111_));
  AOI21  g025(.A0(new_n110_), .A1(new_n109_), .B0(new_n111_), .Y(G423gat));
  NAND2  g026(.A(new_n104_), .B(G390gat), .Y(G446gat));
  INV    g027(.A(G51gat), .Y(new_n114_));
  NOR3   g028(.A(new_n114_), .B(new_n103_), .C(new_n98_), .Y(G447gat));
  INV    g029(.A(G55gat), .Y(new_n116_));
  NOR4   g030(.A(new_n116_), .B(new_n100_), .C(new_n99_), .D(new_n98_), .Y(new_n117_));
  INV    g031(.A(new_n117_), .Y(new_n118_));
  NAND2  g032(.A(G68gat), .B(G29gat), .Y(new_n119_));
  NOR2   g033(.A(new_n119_), .B(new_n118_), .Y(G448gat));
  NAND3  g034(.A(G74gat), .B(G68gat), .C(G59gat), .Y(new_n121_));
  NOR2   g035(.A(new_n121_), .B(new_n118_), .Y(G449gat));
  INV    g036(.A(G89gat), .Y(new_n123_));
  AOI21  g037(.A0(new_n110_), .A1(new_n109_), .B0(new_n123_), .Y(G450gat));
  INV    g038(.A(G130gat), .Y(new_n125_));
  NAND2  g039(.A(G96gat), .B(G91gat), .Y(new_n126_));
  NOR2   g040(.A(G96gat), .B(G91gat), .Y(new_n127_));
  INV    g041(.A(new_n127_), .Y(new_n128_));
  NAND2  g042(.A(G106gat), .B(G101gat), .Y(new_n129_));
  NOR2   g043(.A(G106gat), .B(G101gat), .Y(new_n130_));
  INV    g044(.A(new_n130_), .Y(new_n131_));
  AOI22  g045(.A0(new_n131_), .A1(new_n129_), .B0(new_n128_), .B1(new_n126_), .Y(new_n132_));
  INV    g046(.A(new_n126_), .Y(new_n133_));
  INV    g047(.A(new_n129_), .Y(new_n134_));
  NOR4   g048(.A(new_n130_), .B(new_n134_), .C(new_n127_), .D(new_n133_), .Y(new_n135_));
  NOR3   g049(.A(new_n135_), .B(new_n132_), .C(new_n125_), .Y(new_n136_));
  INV    g050(.A(new_n136_), .Y(new_n137_));
  OAI21  g051(.A0(new_n135_), .A1(new_n132_), .B0(new_n125_), .Y(new_n138_));
  INV    g052(.A(G135gat), .Y(new_n139_));
  NAND2  g053(.A(G116gat), .B(G111gat), .Y(new_n140_));
  NOR2   g054(.A(G116gat), .B(G111gat), .Y(new_n141_));
  INV    g055(.A(new_n141_), .Y(new_n142_));
  NAND2  g056(.A(G126gat), .B(G121gat), .Y(new_n143_));
  NOR2   g057(.A(G126gat), .B(G121gat), .Y(new_n144_));
  INV    g058(.A(new_n144_), .Y(new_n145_));
  AOI22  g059(.A0(new_n145_), .A1(new_n143_), .B0(new_n142_), .B1(new_n140_), .Y(new_n146_));
  INV    g060(.A(new_n140_), .Y(new_n147_));
  INV    g061(.A(new_n143_), .Y(new_n148_));
  NOR4   g062(.A(new_n144_), .B(new_n148_), .C(new_n141_), .D(new_n147_), .Y(new_n149_));
  NOR3   g063(.A(new_n149_), .B(new_n146_), .C(new_n139_), .Y(new_n150_));
  INV    g064(.A(new_n150_), .Y(new_n151_));
  OAI21  g065(.A0(new_n149_), .A1(new_n146_), .B0(new_n139_), .Y(new_n152_));
  AOI22  g066(.A0(new_n152_), .A1(new_n151_), .B0(new_n138_), .B1(new_n137_), .Y(new_n153_));
  INV    g067(.A(new_n138_), .Y(new_n154_));
  INV    g068(.A(new_n152_), .Y(new_n155_));
  NOR4   g069(.A(new_n155_), .B(new_n150_), .C(new_n154_), .D(new_n136_), .Y(new_n156_));
  NOR2   g070(.A(new_n156_), .B(new_n153_), .Y(G767gat));
  NAND2  g071(.A(G165gat), .B(G159gat), .Y(new_n158_));
  NOR2   g072(.A(G165gat), .B(G159gat), .Y(new_n159_));
  INV    g073(.A(new_n159_), .Y(new_n160_));
  NAND2  g074(.A(G177gat), .B(G171gat), .Y(new_n161_));
  NOR2   g075(.A(G177gat), .B(G171gat), .Y(new_n162_));
  INV    g076(.A(new_n162_), .Y(new_n163_));
  AOI22  g077(.A0(new_n163_), .A1(new_n161_), .B0(new_n160_), .B1(new_n158_), .Y(new_n164_));
  INV    g078(.A(new_n158_), .Y(new_n165_));
  INV    g079(.A(new_n161_), .Y(new_n166_));
  NOR4   g080(.A(new_n162_), .B(new_n166_), .C(new_n159_), .D(new_n165_), .Y(new_n167_));
  NOR3   g081(.A(new_n167_), .B(new_n164_), .C(new_n125_), .Y(new_n168_));
  INV    g082(.A(new_n168_), .Y(new_n169_));
  OAI21  g083(.A0(new_n167_), .A1(new_n164_), .B0(new_n125_), .Y(new_n170_));
  INV    g084(.A(G207gat), .Y(new_n171_));
  NAND2  g085(.A(G189gat), .B(G183gat), .Y(new_n172_));
  NOR2   g086(.A(G189gat), .B(G183gat), .Y(new_n173_));
  INV    g087(.A(new_n173_), .Y(new_n174_));
  NAND2  g088(.A(G201gat), .B(G195gat), .Y(new_n175_));
  NOR2   g089(.A(G201gat), .B(G195gat), .Y(new_n176_));
  INV    g090(.A(new_n176_), .Y(new_n177_));
  AOI22  g091(.A0(new_n177_), .A1(new_n175_), .B0(new_n174_), .B1(new_n172_), .Y(new_n178_));
  INV    g092(.A(new_n172_), .Y(new_n179_));
  INV    g093(.A(new_n175_), .Y(new_n180_));
  NOR4   g094(.A(new_n176_), .B(new_n180_), .C(new_n173_), .D(new_n179_), .Y(new_n181_));
  NOR3   g095(.A(new_n181_), .B(new_n178_), .C(new_n171_), .Y(new_n182_));
  INV    g096(.A(new_n182_), .Y(new_n183_));
  OAI21  g097(.A0(new_n181_), .A1(new_n178_), .B0(new_n171_), .Y(new_n184_));
  AOI22  g098(.A0(new_n184_), .A1(new_n183_), .B0(new_n170_), .B1(new_n169_), .Y(new_n185_));
  INV    g099(.A(new_n170_), .Y(new_n186_));
  INV    g100(.A(new_n184_), .Y(new_n187_));
  NOR4   g101(.A(new_n187_), .B(new_n182_), .C(new_n186_), .D(new_n168_), .Y(new_n188_));
  NOR2   g102(.A(new_n188_), .B(new_n185_), .Y(G768gat));
  INV    g103(.A(G261gat), .Y(new_n190_));
  INV    g104(.A(G126gat), .Y(new_n191_));
  INV    g105(.A(G153gat), .Y(new_n192_));
  OAI211 g106(.A0(G42gat), .A1(G17gat), .B0(G156gat), .B1(G59gat), .Y(new_n193_));
  AOI21  g107(.A0(G42gat), .A1(G17gat), .B0(new_n193_), .Y(new_n194_));
  NAND3  g108(.A(G75gat), .B(G59gat), .C(G42gat), .Y(new_n195_));
  NOR4   g109(.A(new_n114_), .B(new_n101_), .C(new_n99_), .D(new_n98_), .Y(new_n196_));
  AOI22  g110(.A0(new_n196_), .A1(new_n195_), .B0(new_n194_), .B1(G447gat), .Y(new_n197_));
  INV    g111(.A(G268gat), .Y(new_n198_));
  NOR3   g112(.A(new_n91_), .B(new_n89_), .C(new_n87_), .Y(new_n199_));
  NAND4  g113(.A(new_n199_), .B(G447gat), .C(new_n198_), .D(G55gat), .Y(new_n200_));
  NAND3  g114(.A(G51gat), .B(G26gat), .C(G1gat), .Y(new_n201_));
  AOI21  g115(.A0(G156gat), .A1(G59gat), .B0(new_n201_), .Y(new_n202_));
  AOI21  g116(.A0(new_n202_), .A1(G17gat), .B0(new_n98_), .Y(new_n203_));
  OAI221 g117(.A0(new_n203_), .A1(new_n192_), .C0(new_n200_), .B0(new_n197_), .B1(new_n191_), .Y(new_n204_));
  NOR2   g118(.A(new_n204_), .B(G201gat), .Y(new_n205_));
  NAND2  g119(.A(new_n204_), .B(G201gat), .Y(new_n206_));
  INV    g120(.A(new_n206_), .Y(new_n207_));
  NOR3   g121(.A(new_n207_), .B(new_n205_), .C(new_n190_), .Y(new_n208_));
  NOR2   g122(.A(new_n207_), .B(new_n205_), .Y(new_n209_));
  OAI21  g123(.A0(new_n209_), .A1(G261gat), .B0(G219gat), .Y(new_n210_));
  NAND2  g124(.A(new_n204_), .B(G246gat), .Y(new_n211_));
  INV    g125(.A(G59gat), .Y(new_n212_));
  NAND3  g126(.A(G73gat), .B(G72gat), .C(G68gat), .Y(new_n213_));
  NOR4   g127(.A(new_n213_), .B(new_n118_), .C(new_n212_), .D(new_n88_), .Y(new_n214_));
  NAND2  g128(.A(new_n214_), .B(G201gat), .Y(new_n215_));
  AOI22  g129(.A0(G267gat), .A1(G255gat), .B0(G210gat), .B1(G121gat), .Y(new_n216_));
  NAND3  g130(.A(new_n216_), .B(new_n215_), .C(new_n211_), .Y(new_n217_));
  AOI221 g131(.A0(new_n209_), .A1(G228gat), .C0(new_n217_), .B0(new_n207_), .B1(G237gat), .Y(new_n218_));
  OAI21  g132(.A0(new_n210_), .A1(new_n208_), .B0(new_n218_), .Y(G850gat));
  INV    g133(.A(G183gat), .Y(new_n220_));
  NAND2  g134(.A(new_n194_), .B(G447gat), .Y(new_n221_));
  NAND2  g135(.A(new_n196_), .B(new_n195_), .Y(new_n222_));
  NAND2  g136(.A(new_n222_), .B(new_n221_), .Y(new_n223_));
  NAND3  g137(.A(G80gat), .B(G75gat), .C(G29gat), .Y(new_n224_));
  NOR4   g138(.A(new_n224_), .B(new_n201_), .C(G268gat), .D(new_n116_), .Y(new_n225_));
  NAND2  g139(.A(new_n202_), .B(G17gat), .Y(new_n226_));
  NAND2  g140(.A(new_n226_), .B(G1gat), .Y(new_n227_));
  AOI221 g141(.A0(new_n227_), .A1(G143gat), .C0(new_n225_), .B0(new_n223_), .B1(G111gat), .Y(new_n228_));
  NAND2  g142(.A(new_n228_), .B(new_n220_), .Y(new_n229_));
  INV    g143(.A(G111gat), .Y(new_n230_));
  INV    g144(.A(G143gat), .Y(new_n231_));
  OAI221 g145(.A0(new_n203_), .A1(new_n231_), .C0(new_n200_), .B0(new_n197_), .B1(new_n230_), .Y(new_n232_));
  NAND2  g146(.A(new_n232_), .B(G183gat), .Y(new_n233_));
  INV    g147(.A(G116gat), .Y(new_n234_));
  INV    g148(.A(G146gat), .Y(new_n235_));
  OAI221 g149(.A0(new_n203_), .A1(new_n235_), .C0(new_n200_), .B0(new_n197_), .B1(new_n234_), .Y(new_n236_));
  INV    g150(.A(G121gat), .Y(new_n237_));
  INV    g151(.A(G149gat), .Y(new_n238_));
  OAI221 g152(.A0(new_n203_), .A1(new_n238_), .C0(new_n200_), .B0(new_n197_), .B1(new_n237_), .Y(new_n239_));
  OAI22  g153(.A0(new_n239_), .A1(G195gat), .B0(new_n236_), .B1(G189gat), .Y(new_n240_));
  OAI21  g154(.A0(new_n204_), .A1(G201gat), .B0(G261gat), .Y(new_n241_));
  NOR2   g155(.A(new_n241_), .B(new_n240_), .Y(new_n242_));
  NOR2   g156(.A(new_n236_), .B(G189gat), .Y(new_n243_));
  NAND2  g157(.A(new_n236_), .B(G189gat), .Y(new_n244_));
  NAND2  g158(.A(new_n239_), .B(G195gat), .Y(new_n245_));
  OAI221 g159(.A0(new_n245_), .A1(new_n243_), .C0(new_n244_), .B0(new_n240_), .B1(new_n206_), .Y(new_n246_));
  OAI211 g160(.A0(new_n246_), .A1(new_n242_), .B0(new_n233_), .B1(new_n229_), .Y(new_n247_));
  INV    g161(.A(new_n229_), .Y(new_n248_));
  INV    g162(.A(new_n233_), .Y(new_n249_));
  NOR2   g163(.A(new_n246_), .B(new_n242_), .Y(new_n250_));
  OAI21  g164(.A0(new_n249_), .A1(new_n248_), .B0(new_n250_), .Y(new_n251_));
  NAND3  g165(.A(new_n251_), .B(new_n247_), .C(G219gat), .Y(new_n252_));
  NOR2   g166(.A(new_n249_), .B(new_n248_), .Y(new_n253_));
  INV    g167(.A(G237gat), .Y(new_n254_));
  INV    g168(.A(G246gat), .Y(new_n255_));
  AOI22  g169(.A0(new_n214_), .A1(G183gat), .B0(G210gat), .B1(G106gat), .Y(new_n256_));
  OAI221 g170(.A0(new_n233_), .A1(new_n254_), .C0(new_n256_), .B0(new_n228_), .B1(new_n255_), .Y(new_n257_));
  AOI21  g171(.A0(new_n253_), .A1(G228gat), .B0(new_n257_), .Y(new_n258_));
  NAND2  g172(.A(new_n258_), .B(new_n252_), .Y(G863gat));
  INV    g173(.A(new_n244_), .Y(new_n260_));
  OAI221 g174(.A0(new_n239_), .A1(G195gat), .C0(G261gat), .B0(new_n204_), .B1(G201gat), .Y(new_n261_));
  NOR2   g175(.A(new_n239_), .B(G195gat), .Y(new_n262_));
  INV    g176(.A(new_n262_), .Y(new_n263_));
  INV    g177(.A(new_n245_), .Y(new_n264_));
  AOI21  g178(.A0(new_n263_), .A1(new_n207_), .B0(new_n264_), .Y(new_n265_));
  AOI211 g179(.A0(new_n265_), .A1(new_n261_), .B(new_n260_), .C(new_n243_), .Y(new_n266_));
  OAI211 g180(.A0(new_n260_), .A1(new_n243_), .B0(new_n265_), .B1(new_n261_), .Y(new_n267_));
  NAND2  g181(.A(new_n267_), .B(G219gat), .Y(new_n268_));
  NOR2   g182(.A(new_n260_), .B(new_n243_), .Y(new_n269_));
  NAND2  g183(.A(new_n236_), .B(G246gat), .Y(new_n270_));
  NAND2  g184(.A(new_n214_), .B(G189gat), .Y(new_n271_));
  AOI22  g185(.A0(G259gat), .A1(G255gat), .B0(G210gat), .B1(G111gat), .Y(new_n272_));
  NAND3  g186(.A(new_n272_), .B(new_n271_), .C(new_n270_), .Y(new_n273_));
  AOI221 g187(.A0(new_n269_), .A1(G228gat), .C0(new_n273_), .B0(new_n260_), .B1(G237gat), .Y(new_n274_));
  OAI21  g188(.A0(new_n268_), .A1(new_n266_), .B0(new_n274_), .Y(G864gat));
  AOI211 g189(.A0(new_n241_), .A1(new_n206_), .B(new_n264_), .C(new_n262_), .Y(new_n276_));
  OAI211 g190(.A0(new_n264_), .A1(new_n262_), .B0(new_n241_), .B1(new_n206_), .Y(new_n277_));
  NAND2  g191(.A(new_n277_), .B(G219gat), .Y(new_n278_));
  NAND3  g192(.A(new_n245_), .B(new_n263_), .C(G228gat), .Y(new_n279_));
  NAND2  g193(.A(new_n214_), .B(G195gat), .Y(new_n280_));
  AOI22  g194(.A0(G260gat), .A1(G255gat), .B0(G210gat), .B1(G116gat), .Y(new_n281_));
  NAND2  g195(.A(new_n281_), .B(new_n280_), .Y(new_n282_));
  AOI221 g196(.A0(new_n264_), .A1(G237gat), .C0(new_n282_), .B0(new_n239_), .B1(G246gat), .Y(new_n283_));
  OAI211 g197(.A0(new_n278_), .A1(new_n276_), .B0(new_n283_), .B1(new_n279_), .Y(G865gat));
  INV    g198(.A(G159gat), .Y(new_n285_));
  INV    g199(.A(G91gat), .Y(new_n286_));
  AOI211 g200(.A0(G156gat), .A1(G59gat), .B(new_n201_), .C(new_n116_), .Y(new_n287_));
  NOR4   g201(.A(new_n224_), .B(new_n201_), .C(G268gat), .D(new_n101_), .Y(new_n288_));
  AOI221 g202(.A0(new_n287_), .A1(G143gat), .C0(new_n288_), .B0(G138gat), .B1(G8gat), .Y(new_n289_));
  OAI21  g203(.A0(new_n197_), .A1(new_n286_), .B0(new_n289_), .Y(new_n290_));
  INV    g204(.A(new_n290_), .Y(new_n291_));
  NOR2   g205(.A(new_n291_), .B(new_n285_), .Y(new_n292_));
  INV    g206(.A(new_n292_), .Y(new_n293_));
  INV    g207(.A(G96gat), .Y(new_n294_));
  INV    g208(.A(G165gat), .Y(new_n295_));
  AOI221 g209(.A0(new_n287_), .A1(G146gat), .C0(new_n288_), .B0(G138gat), .B1(G51gat), .Y(new_n296_));
  OAI211 g210(.A0(new_n197_), .A1(new_n294_), .B0(new_n296_), .B1(new_n295_), .Y(new_n297_));
  INV    g211(.A(G101gat), .Y(new_n298_));
  INV    g212(.A(G171gat), .Y(new_n299_));
  AOI221 g213(.A0(new_n287_), .A1(G149gat), .C0(new_n288_), .B0(G138gat), .B1(G17gat), .Y(new_n300_));
  OAI211 g214(.A0(new_n197_), .A1(new_n298_), .B0(new_n300_), .B1(new_n299_), .Y(new_n301_));
  NAND2  g215(.A(new_n301_), .B(new_n297_), .Y(new_n302_));
  INV    g216(.A(G106gat), .Y(new_n303_));
  INV    g217(.A(new_n287_), .Y(new_n304_));
  AOI21  g218(.A0(G152gat), .A1(G138gat), .B0(new_n288_), .Y(new_n305_));
  OAI221 g219(.A0(new_n304_), .A1(new_n192_), .C0(new_n305_), .B0(new_n197_), .B1(new_n303_), .Y(new_n306_));
  NOR2   g220(.A(new_n306_), .B(G177gat), .Y(new_n307_));
  OAI21  g221(.A0(new_n246_), .A1(new_n242_), .B0(new_n229_), .Y(new_n308_));
  AOI211 g222(.A0(new_n308_), .A1(new_n233_), .B(new_n307_), .C(new_n302_), .Y(new_n309_));
  NAND2  g223(.A(new_n306_), .B(G177gat), .Y(new_n310_));
  OAI21  g224(.A0(new_n197_), .A1(new_n294_), .B0(new_n296_), .Y(new_n311_));
  NAND2  g225(.A(new_n311_), .B(G165gat), .Y(new_n312_));
  OAI21  g226(.A0(new_n197_), .A1(new_n298_), .B0(new_n300_), .Y(new_n313_));
  NAND3  g227(.A(new_n313_), .B(new_n297_), .C(G171gat), .Y(new_n314_));
  OAI211 g228(.A0(new_n310_), .A1(new_n302_), .B0(new_n314_), .B1(new_n312_), .Y(new_n315_));
  NOR2   g229(.A(new_n315_), .B(new_n309_), .Y(new_n316_));
  OAI211 g230(.A0(new_n197_), .A1(new_n286_), .B0(new_n289_), .B1(new_n285_), .Y(new_n317_));
  INV    g231(.A(new_n317_), .Y(new_n318_));
  OAI21  g232(.A0(new_n318_), .A1(new_n316_), .B0(new_n293_), .Y(G866gat));
  INV    g233(.A(new_n307_), .Y(new_n320_));
  NAND2  g234(.A(new_n310_), .B(new_n320_), .Y(new_n321_));
  AOI21  g235(.A0(new_n308_), .A1(new_n233_), .B0(new_n321_), .Y(new_n322_));
  NAND3  g236(.A(new_n321_), .B(new_n308_), .C(new_n233_), .Y(new_n323_));
  NAND2  g237(.A(new_n323_), .B(G219gat), .Y(new_n324_));
  NAND3  g238(.A(new_n310_), .B(new_n320_), .C(G228gat), .Y(new_n325_));
  INV    g239(.A(new_n310_), .Y(new_n326_));
  INV    g240(.A(G210gat), .Y(new_n327_));
  NAND2  g241(.A(new_n214_), .B(G177gat), .Y(new_n328_));
  OAI21  g242(.A0(new_n327_), .A1(new_n298_), .B0(new_n328_), .Y(new_n329_));
  AOI221 g243(.A0(new_n326_), .A1(G237gat), .C0(new_n329_), .B0(new_n306_), .B1(G246gat), .Y(new_n330_));
  OAI211 g244(.A0(new_n324_), .A1(new_n322_), .B0(new_n330_), .B1(new_n325_), .Y(G874gat));
  NOR2   g245(.A(new_n318_), .B(new_n292_), .Y(new_n332_));
  OAI21  g246(.A0(new_n315_), .A1(new_n309_), .B0(new_n332_), .Y(new_n333_));
  OAI21  g247(.A0(new_n318_), .A1(new_n292_), .B0(new_n316_), .Y(new_n334_));
  NAND3  g248(.A(new_n334_), .B(new_n333_), .C(G219gat), .Y(new_n335_));
  AOI22  g249(.A0(new_n214_), .A1(G159gat), .B0(G268gat), .B1(G210gat), .Y(new_n336_));
  OAI21  g250(.A0(new_n291_), .A1(new_n255_), .B0(new_n336_), .Y(new_n337_));
  AOI221 g251(.A0(new_n332_), .A1(G228gat), .C0(new_n337_), .B0(new_n292_), .B1(G237gat), .Y(new_n338_));
  NAND2  g252(.A(new_n338_), .B(new_n335_), .Y(G878gat));
  NAND2  g253(.A(new_n312_), .B(new_n297_), .Y(new_n340_));
  NAND2  g254(.A(new_n313_), .B(G171gat), .Y(new_n341_));
  INV    g255(.A(new_n341_), .Y(new_n342_));
  INV    g256(.A(new_n301_), .Y(new_n343_));
  AOI211 g257(.A0(new_n308_), .A1(new_n233_), .B(new_n307_), .C(new_n343_), .Y(new_n344_));
  AOI211 g258(.A0(new_n326_), .A1(new_n301_), .B(new_n344_), .C(new_n342_), .Y(new_n345_));
  INV    g259(.A(G219gat), .Y(new_n346_));
  AOI21  g260(.A0(new_n345_), .A1(new_n340_), .B0(new_n346_), .Y(new_n347_));
  OAI21  g261(.A0(new_n345_), .A1(new_n340_), .B0(new_n347_), .Y(new_n348_));
  NAND3  g262(.A(new_n312_), .B(new_n297_), .C(G228gat), .Y(new_n349_));
  NAND3  g263(.A(new_n311_), .B(G237gat), .C(G165gat), .Y(new_n350_));
  NOR2   g264(.A(new_n327_), .B(new_n286_), .Y(new_n351_));
  AOI221 g265(.A0(new_n311_), .A1(G246gat), .C0(new_n351_), .B0(new_n214_), .B1(G165gat), .Y(new_n352_));
  NAND4  g266(.A(new_n352_), .B(new_n350_), .C(new_n349_), .D(new_n348_), .Y(G879gat));
  NAND2  g267(.A(new_n308_), .B(new_n233_), .Y(new_n354_));
  NAND2  g268(.A(new_n354_), .B(new_n320_), .Y(new_n355_));
  AOI211 g269(.A0(new_n310_), .A1(new_n355_), .B(new_n342_), .C(new_n343_), .Y(new_n356_));
  OAI211 g270(.A0(new_n342_), .A1(new_n343_), .B0(new_n310_), .B1(new_n355_), .Y(new_n357_));
  NAND2  g271(.A(new_n357_), .B(G219gat), .Y(new_n358_));
  NAND3  g272(.A(new_n341_), .B(new_n301_), .C(G228gat), .Y(new_n359_));
  NAND2  g273(.A(new_n214_), .B(G171gat), .Y(new_n360_));
  OAI21  g274(.A0(new_n327_), .A1(new_n294_), .B0(new_n360_), .Y(new_n361_));
  AOI221 g275(.A0(new_n342_), .A1(G237gat), .C0(new_n361_), .B0(new_n313_), .B1(G246gat), .Y(new_n362_));
  OAI211 g276(.A0(new_n358_), .A1(new_n356_), .B0(new_n362_), .B1(new_n359_), .Y(G880gat));
endmodule


