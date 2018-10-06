<template>
  <div>
    <van-tabs v-model="tabsActive">
      <van-tab title="商业B1F">
        <div>
          <van-collapse v-model="activeNamesB1" accordion>
            <van-collapse-item title="明星时代影城" name="1">
              <img class="collapse-img" src="https://p1.dodoca.com/org/c/3d/3ab/9372/0d90a58382c8e3df962cb9.jpg" />
              <div>
                <p class="collapse-p">北京明星时代影院管理有限公司(STARTIME)成立于2015年，注册资本1亿元，由生命人寿（中国第五大保险集团）出资的厚德前海基金领投组建，资本实力雄厚。STARTIME以控权北京世茂电影院线有限公司为基础，打造第三代互动电影文化影城，构建全互联网覆盖的高品质互动影厅。</p>
                <p class="collapse-p">明星时代影城上海富荟店，位于富荟广场地下一层，共624个座位，8个厅，最新数字激光放映，环立体音效，高清晰3D效果。</p>
                <p class="collapse-p">联系电话：021-58585638</p>
              </div>
            </van-collapse-item>
          </van-collapse>
        </div>
      </van-tab>
      <van-tab title="商业1F">
        <div>
          <van-collapse v-model="activeNames1F" accordion>
            <van-collapse-item title="星巴克 B-1" name="1">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201608/zLS8yOC3_oge2.png" />
              <div>
                <p class="collapse-p">星巴克（Starbucks）是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。星巴克旗下零售产品包括30多款全球顶级的咖啡豆、手工制作的浓缩咖啡和多款咖啡冷热饮料、新鲜美味的各式糕点食品以及丰富多样的咖啡机、咖啡杯等商品。星巴克在全球范围内已经有近21300间分店遍布北美、南美洲、欧洲、中东及太平洋区。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/35/8f5/b9e9/9a6c5e4412e5529fdf8c4d.jpg" />
            </van-collapse-item>
            <van-collapse-item title="The Frypan韩国炸鸡&啤酒 B-3" name="2">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/1zqOQ0wD_MfXE.png" />
              <div>
                <p class="collapse-p">The Frypan韩国炸鸡&啤酒，一个来自韩国本土的著名炸鸡休闲餐饮品牌。品牌创立至今10多年，专注于韩式炸鸡及韩国主流小吃的研发，致力于给顾客带来最纯正的韩式炸鸡饮食文化。为了保证每份炸鸡鲜嫩的口感，我们坚持使用“冰鲜”鸡而非“冰冻”鸡。秉承着“绿色健康、品质至上”的原则，“用炸鸡征服世界~！”</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/2e/ff5/a136/d541aa70bb9d6fb92dd5a4.jpg" />
            </van-collapse-item>
            <van-collapse-item title="easy 家乐福 B-5" name="3">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/iIUNvjhE_9jvc.png" />
              <div>
                <p class="collapse-p">家乐福便利店是家乐福在中国独创的品牌，打造邻家社区型便利店概念，旨在通过丰富商品及优质服务满足社区居民基本生活之需，把便捷的生活方式带给周边消费者。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/e4/d14/5e80/5b5f764d8f8c78b101f686.jpg" />
            </van-collapse-item>
            <van-collapse-item title="双鱼座云南菜 B-6-7" name="4">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/UQLKUenC_D01P.png" />
              <div>
                <p class="collapse-p"> 双鱼座餐厅源于千年的古渔村双廊，源于半个世纪的古渔香饭店，源于家族传承的以酸辣鱼为代表的十二仙味。上海美勺餐饮管理有限公司，搭建以餐饮美食为核心，综合植物花艺和音乐艺术为两翼的双鱼座平台，希望人们的味蕾在被挑逗的状态下，听觉和视觉都会有不一样的感受，进而吸引你走进白族走进双廊，走进自己的诗情画意，走向心中的远方。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/a/8e/9a4/0933/1d4a843ec177471cd0c366.jpg" />
            </van-collapse-item>
            <van-collapse-item title="ALAMI B-8" name="5">
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/d0/ec2/47e0/f3db43c751ac268e01176d.jpg" />
              <div>
                <p class="collapse-p">我们来自中东最古老的国家——沙特阿拉伯</p>
                <p class="collapse-p">我们来自于一个悠久的贵族部落，其历史可追溯到丝绸之路的发源</p>
                <p class="collapse-p">我们的身影遍布整个阿拉伯世界 并正一步步迈向全球</p>
                <p class="collapse-p">我们来到中国 传播我们的餐饮文化</p>
                <p class="collapse-p">我们秉持着可口、新鲜、健康，带您发现不一样的地中海中东美食</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/2a/5ce/e2fb/b2c72a94ad41ca35c7a3a0.jpg" />
            </van-collapse-item>
            <van-collapse-item title="小浦东 B-9" name="6">
              <img class="collapse-img" src="https://p1.dodoca.com/org/6/a6/85e/5928/01144db75567fcd419265b.jpg" />
              <div>
                <p class="collapse-p">其实做白斩鸡生意的店在上海还是很多的，因为上海人特别爱吃白斩鸡。小浦东鸡粥店开了“好多年”了，生意一直都“很兴旺”。当家的白斩鸡“又香又嫩”，蘸料“非常适合上海人的口味”；鸡粥“不厚不薄”；鸡汤面“价廉物美”。环境和服务都是“老样子”，不想堂吃的话就外带回家咯。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/f/ca/85d/7b85/538bee4f772be244ed2eb9.jpg" />
            </van-collapse-item>
            <van-collapse-item title="味霸面馆 B-10" name="7">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/28ipzung_bf7O.png" />
              <div>
                <p class="collapse-p">味霸面馆专注面食文化的传承和发扬，特选地地道道的巴渝食材，进行原汁原味烹制，在原有基础上把重庆小面系列得以升华呈现在您的面前，面条劲道可口，汤鲜而味厚，鲜香麻辣挑战您舌尖上的味蕾，味霸面馆是您品味面食文化的的上上之选</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/b8/80d/e491/8e5ab91856d15c24277140.jpg" />
            </van-collapse-item>
            <van-collapse-item title="沙绿 B-11" name="8">
              <img class="collapse-img" src="https://p1.dodoca.com/org/c/7f/6ab/6625/9575d9aa4e976e72956831.jpg" />
              <div>
                <p class="collapse-p">沙绿轻食由大众点评8号员工创立，产品一经上线便风靡魔都，迅速聚拢全上海50万轻食主义者。营养师专业配比，采购严选食材，独家手工酱汁秘方。除了沙拉，沙绿轻食门店更有Poke饭、谷物碗、三明治、营养杯、鲜榨果汁等丰富的产品供用户选择，每一款均颇受广大用户喜爱，且获得2017中国餐厅外卖大会顶尖颜值品牌。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/a/95/d71/0198/af817b6ae1e13d6af9921f.jpg" />
            </van-collapse-item>
            <van-collapse-item title="也马饭店 B-12" name="9">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/s9zlKspk_GTn0.jpg" />
              <div>
                <p class="collapse-p">也马，不仅仅一家日料餐厅，同时也是一个社群，一本文艺杂志，代表着一种价值观和审美。也马餐厅不同于传统的日料，除了传统的寿司、刺身、烤物、炸物、定食等料理，我们还特地请了资深的西餐厨师一起研发了一些结合西餐做法的和风创意菜。另外，牛排和寿喜锅是我们的主打产品。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/99/725/4618/605bee82cea30b2b7bb0d9.jpg" />
            </van-collapse-item>
            <van-collapse-item title="ONLISA私人定制 B-15" name="10">
              <div>
                <p class="collapse-p">ONLISA是一家高级定制服装店，高级定制代表着一种奢华的生活方式，既有不输于名牌的设计感，同时又是适合自己且独一无二的衣服。始终致力于高级成衣定制行业，以精美做工和体贴服务而为人称道，个性化的量体裁剪。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/9e/e9c/ef6c/c422faaea6eca19c744832.jpg" />
            </van-collapse-item>
            <van-collapse-item title="ZOO COFFEE B-16" name="11">
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/d7/2f0/3106/55329c411f944f954d71a0.jpg" />
              <div>
                <p class="collapse-p">ZOO COFFEE于2009年在韩国创始，2012年进入中国后得以飞速发展，内涵也更加丰富，成为知名的以动物文化为主题的连锁咖啡品牌。</p>
                <p class="collapse-p">店面亲近自然的装修风格中有着许多独特的元素。动物模型、壁画和光影......传递着自然、纯粹的味道，更传达了渗透了人和自然相互守望的品牌精神，因而造就了ZOO COFFEE独树一帜的，极具亲和力的咖啡文化。</p>
                <p class="collapse-p">ZOO COFFEE秉持着“将动物园中的欢声笑语和轻松愉悦带到都市当中来”的初衷，致力于成为人们心中“都市中的乐园”，为在喧闹的都市中奔波的人们提供一个宁静、闲适、没好的休闲绿洲。</p>
              </div>
              <img class="collapse-img" src="http://p1.dodoca.com/org/7/ad/79e/4085/66a561456f32d3e2c9f454.jpg" />
              <img class="collapse-img" src="https://p1.dodoca.com/org/1/76/1ec/1475/2aff3dc9f1761b6960e733.jpg" />
            </van-collapse-item>
            <van-collapse-item title="巴黎贝甜 B-17" name="12">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/kvr0agwc_RfiH.png" />
              <div>
                <p class="collapse-p">巴黎贝甜是艾丝碧西属下的品牌，主要经营法式面包、新鲜三明治、美味蛋糕、纯正咖啡。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/4e/1ce/e92e/73da4adb9a8fe9f2340092.jpg" />
            </van-collapse-item>
            <van-collapse-item title="食其家 B-18" name="13">
              <div>
                <p class="collapse-p">「食其家（すき家）」创立于日本横滨，以牛丼作为主力商品，在日本国内以及海外以直营店的方式扩展。食其家产品种类丰富，从家庭到个人辐射客层广，受到人们的广泛喜爱。店铺内配置有方便一人用餐的吧台坐席，也配置有方便团体/家庭客人用餐的桌椅坐席。多样丰富的菜品，适宜的价格和让客人感到感动的速度和良好的态度。食其家使用的食材是在原产地就严选出的高品质的原料，通过工厂进行品质管理和彻底加工，每天都保持最新鲜状态下来配送到店铺，通过一整套流程来确保品质。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/e5/566/5c94/81645aa4b812926c4d3a84.jpg" />
            </van-collapse-item>
            <van-collapse-item title="沧浪亭 B-19" name="14">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/SqlH3gvQ_rLR2.png" />
              <div>
                <p class="collapse-p">沧浪亭是以经营面点、糕团而闻名于世，自1990年引进菜肴，现已形成自己的独特风格，自创的三虾面至今独家经营，葱油肉丝面、虾蟹面、百果松糕、条头糕、三鲜鱼肚、蟹粉口蘑等数十个品种，曾获得中商部国内贸易局、上海市饮食行业协会、上海市饭店业协会的各种奖励。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/1/87/e2c/903a/d3bf939781d6a53565d902.jpg" />
            </van-collapse-item>
            <van-collapse-item title="海尚蒸 B-20" name="15">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/4xJi0Glm_mV15.jpg" />
              <div>
                <p class="collapse-p">海尚蒸属于上海禾嘉餐饮管理有限公司旗下品牌，是一家以中国传统民间美食蒸汽为主的餐厅。我们摒弃固有的开水白灼式海鲜食用方法，采用最新研发的蒸汽锅，在保留海鲜营养和食用价值的同时关注到了海鲜的鲜美度。应广大消费者的需求和养生理念，我们选用健康新鲜的食材，不加任何添加剂和多余的调味料，呈现原生态的海鲜料理。在食品安全方面，我们特意在现场为您烹饪，使您味觉和视觉达到双重享受。海尚蒸致力于做适合大众口味的海鲜蒸菜，我们有研究多年上海本土菜，在餐饮方面有相对多的经验，我们相信能打造出经济实惠，老少皆宜海鲜蔬菜。海尚蒸欢迎您来品鉴。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/f/18/3c0/29e1/34b9bab7fa4a2e2ee2f71a.jpg" />
            </van-collapse-item>
            <van-collapse-item title="眉州小吃 B-21" name="16">
              <img class="collapse-img" src="http://p1.dodoca.com/org/6/16/d6d/d5db/774059ce853f7dbb50197a.jpg" />
              <div>
                <p class="collapse-p">眉州小吃，创立于一九九九年，以继承、发扬传统经典小吃为己任。如今，已发展至四十多家连锁店。四川小吃，家喻户晓，巴蜀之地，自古有句老话叫：“走州吃州，走县吃县”的说法。小吃不小，名扬四方。招牌担担面，经历170年的变迁，在走街串巷的担担里，颠簸出一种淡淡的生活哲学，来一碗担担面，让我们好好享受生活中淡淡的味道。眉州小吃，来自四川的味道。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/7/a6/8c8/b8a6/7d479ac5ae3681343f178f.jpg" />
            </van-collapse-item>
            <van-collapse-item title="德克士 B-22" name="17">
              <img class="collapse-img" src="http://p1.dodoca.com/org/2/32/d2c/b0f6/9db9a708d72ac1fa90734b.png" />
              <div>
                <p class="collapse-p">“德克士”是创立康师傅品牌的顶新国际集团于1996年5月管理的西式快餐连锁品牌，到目前为止，全国有2000多家餐厅。德克士秉持勇于追求的经营理念，遍寻世界美食佳肴，坚持严选优质食材，不断提升服务质量。让消费者{在德克士感受最佳的用餐体验}</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/7/93/890/41ff/425ca3f9adabd2055cbd3e.jpg" />
            </van-collapse-item>
            <van-collapse-item title="西城港式茶餐厅 B-23-25" name="18">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/2P4zFPTj_lqmd.png" />
              <div>
                <p class="collapse-p">“西城港式茶餐厅”茶餐厅专业致力于打造香港传统及现代创新经营餐饮连锁事业，拥有专业化、规范化，成熟化品牌管理体系。餐厅具有浓厚的复古文化风韵与现代气息完美的融合；依托资深香港的烹饪团队，呈现港式风味：港粤烧腊、靓小炒、港式茶点、老火靓汤、健康饮品等。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/5/de/137/79cb/e6f649823667f7babdd588.jpg" />
            </van-collapse-item>
            <van-collapse-item title="软胖胖煎饺 B-26" name="19">
              <div>
                <p class="collapse-p">精准火候烹炸出的软胖胖煎饺，饺皮酥脆爽口，饺馅美味鲜香，作正餐、小食、夜宵均可，是江南传统美食中不可错过的经典佳作。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/cc/6d4/6258/b57e67be722359557baf3f.jpg" />
            </van-collapse-item>
            <van-collapse-item title="伊帕尔汗 B-27-28" name="20">
              <img class="collapse-img" src="https://p1.dodoca.com/org/4/76/aef/d1c5/a50263ab55d728b40d24df.jpg" />
              <div>
                <p class="collapse-p">随着社会的不断发展，人们生活方式及消费习惯的转变，对消费载体不再是单一的诉求，对品质的追求越来越高，为迎合社会发展进程，上海伊帕尔汗餐饮集团提出了打造一个颠覆传统饮食行业，集“品味美食、歌舞娱乐、商务服务”为一体的餐饮品牌——伊帕尔汗。定位于以白领商务及社区人员为主要客户群、“美食、西域风情、商务”为定位、直营连锁为经营模式的餐饮品牌。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/f/5b/19d/8975/364cd5bd2ecc35cae64abb.jpg" />
            </van-collapse-item>
            <van-collapse-item title="MODS HAIR美发沙龙 B-29" name="21">
              <img class="collapse-img" src="http://image.135editor.com/files/users/109/1096933/201609/o7BQVQoZ_Uf2g.png" />
              <div>
                <p class="collapse-p">1968年，MH品牌于法国巴黎诞生，并于1974年在巴黎开设第一家美发沙龙，此后逐渐拓展到加拿大、意大利、德国、希腊、韩国等14个国家和地区。MH进入中国，结合中医头疗、健康打造不二发门独门养发养脑中医科技需求，全服务过程给顾客带来健康美丽的完整体验。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/7/bf/8e6/580d/a5604e103038774ce20020.jpg" />
            </van-collapse-item>
            <van-collapse-item title="老人和饭店 B-30" name="22">
              <img class="collapse-img" src="http://p1.dodoca.com/org/d/af/f2a/0535/e15bb04cf1072039cfbb63.jpg" />
              <div>
                <p class="collapse-p">“老人和”品牌创始于清朝嘉庆年间(约1800年)，是现存历史最悠久的本帮餐馆。烹制的菜肴以本帮为特色，该店以红烧浓油赤酱为技法，尤以“吊糟卤法”形成了独具一格的糟货系列。老人和本帮特色菜“响油鳝丝”、“油爆虾”、“虾子大乌参”，糟货招牌菜“糟猪肚”、“糟猪尾”、“糟钵斗”等菜式被上海市餐饮烹饪行业协会认定为“上海名菜”。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/e/2f/dfe/27de/c3de65ab1905b3e01eb1e2.jpg" />
            </van-collapse-item>
            <van-collapse-item title="迪卡坐标 B-31 " name="23">
              <div>
                <p class="collapse-p">迪卡坐标数学俱乐部引进基于美国州际共同核心标准(CCSS)的美国My Math数学课程，为3-15岁孩子提供以“主动思考”、“数学表达”和“解决问题”为核心的系统化、高品质数学教育。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/a/f5/e3a/e541/1db9e3bc7c60b337900a4a.jpg" />
            </van-collapse-item>
            <van-collapse-item title="开心丽果鲜榨果汁吧 B-32" name="24">
              <img class="collapse-img" src="https://p1.dodoca.com/org/c/02/4c4/82b8/45a66b06932e6198efe21e.png" />
              <div>
                <p class="collapse-p">开心丽果-天然果蔬营养专家，新鲜果汁连锁品牌，来源于美式健康理念，于2012年在中国海派之都-上海成立，主要经营无糖、无水、无添加的鲜榨果蔬汁及其衍生产品，同时，向广大消费者传达健康有爱的果蔬生活理念于品牌创新。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/2/6d/3b3/387d/d80138d3842421e710d679.jpg" />
            </van-collapse-item>
            <van-collapse-item title="快乐柠檬 B-33" name="25">
              <img class="collapse-img" src="https://p1.dodoca.com/org/c/2e/0a7/743a/e709f2aa7b60281c1c1f6a.png" />
              <div>
                <p class="collapse-p">快乐柠檬创立于2006年，品牌形象鲜明、有活力，运用鲜明的色彩、简单的线条，营造出开心、活泼、健康、可爱的店面形象，让消费者在视觉上更容易辨识。快乐柠檬以年轻族群为主要目标客群，结合健康、流行与时尚趋势，提供高品质、健康、创新的饮品给消费者。快乐柠檬旨在发扬『新茶文化』，茶是华人的文化，快乐柠檬(happylemon)让茶成为世界的文化。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/5/80/a7c/1782/9daa984f0565d06c802b66.jpg" />
            </van-collapse-item>
            <van-collapse-item title="FLORIST DL花店 B-35" name="26">
              <img class="collapse-img" src="https://p1.dodoca.com/org/f/1b/454/9dd4/1de66f56842f6aa4029b43.jpg" />
              <div>
                <p class="collapse-p">FLORIST DL是新一代的精品欧式花店，致力于为全中国客户提供最用心的鲜花与礼品解决方案。我们明白您的情思与心动，精心设计、打造每一款花盒、礼品。执着于更诚挚、更用心地表达你我的情感，只为您和您在乎的那个人。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/1/55/708/dbad/c0d6880c7faa08b74f50f5.jpg" />
            </van-collapse-item>
            <van-collapse-item title="留夫鸭 B-36" name="27">
              <img class="collapse-img" src="http://p1.dodoca.com/org/e/69/27b/700e/1d9fdea7fbc5d7fd5658ba.jpg" />
              <div>
                <p class="collapse-p">留夫鸭是属于上海优亿食品有限公司旗下的一个卤味连锁店品牌，优亿食品公司成立于2008年，旗下有品牌连锁店留夫鸭，“留夫”品牌注册于2005年11月28日。公司主要生产特色熟食系列产品。采用现代标准生产工艺，用三十余种中草药烹制出美味健康的风味小吃，其以麻、辣、香、甜为主要特色，以不添加任何色素、亚硝酸钠、防腐剂为生产原则。现有三大类产品（餐桌、附件、素食）三种口味（不辣、微辣、重辣）总共品种达到60种以上，并且不断推出季节性食品，保证门店的品类容量，最大限度满足各个阶层的顾客在熟食食品方面的需求。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/8e/7b7/0a47/32551b0ccb8051d8178d5b.jpg" />
            </van-collapse-item>
            <van-collapse-item title="福奈特 B-37" name="28">
              <img class="collapse-img" src="http://p1.dodoca.com/org/2/63/d9e/e013/ffcaf15f12110e18fc2014.png" />
              <div>
                <p class="collapse-p">福奈特洗衣成立于1997年7月，其下的门店已经发展到1000多家，遍布全国31个省市和自治区的200多个城市。FORNET知道自己在中国的目标，那就是用高效率的运作方式和优质的洗衣服务，洁净我们的衣物，美化我们的生活，使消费者不出国门就能享受到国际最高水平的洗衣服务！</p>
                <p class="collapse-p">FORNET was founded in July, 1997. Currently, more than 1000 shops in 200 cities of 31 provinces and autonomous regions around China. FORNET knows exactly what its target in China, that is, to clean our clothes and to beautify our life through highly efficient operation and high-quality laundry service for the purpose to make consumers in China enjoy laundry service at the highest level in the world at home!</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/9/25/61b/c294/8195e93b8b29089a15c2e1.jpg" />
            </van-collapse-item>
            <van-collapse-item title="华为授权专卖店 B-39" name="29">
            </van-collapse-item>
            <van-collapse-item title="LOOPUROR童装 B-40 " name="30">
            </van-collapse-item>
            <van-collapse-item title="桂源铺 B-41" name="31">
            </van-collapse-item>
            <van-collapse-item title="烘焙龙 B-42" name="32">
              <img class="collapse-img" src="https://p1.dodoca.com/org/c/ff/b36/a84d/1c4c01639383fa1a034e06.jpg" />
            </van-collapse-item>
            <van-collapse-item title="小桃园 B-60" name="33">
            </van-collapse-item>
            <div style="height: 50px;"></div>
          </van-collapse>
        </div>
      </van-tab>
      <van-tab title="商业2F">
        <div>
          <van-collapse v-model="activeNames2F" accordion>
            <van-collapse-item title="能力风暴 B-201" name="1">
              <div>
                <p class="collapse-p">能力风暴®（ABILIX®）是教育机器人的全球开创者和领导者，于1998年在全球率先发布了第一台教育机器人，致力于为学校提供教育机器人，目前已有31个国家一万多个学校用户，致力于创建直营和特许经营的能力风暴机器人活动中心，为社区学生提供成功能力的训练场，致力于为家庭提供教育机器人。能力风暴教育机器人是世界教育机器人大赛（WER）唯一比赛平台。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/1/e0/fe1/4ef5/4546710b3494898f61b7c1.jpg" />
            </van-collapse-item>
            <van-collapse-item title="CICI纤美 B-202" name="2">
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/91/420/59a1/ce366668978ae8dfec23af.jpg" />
              <div>
                <p class="collapse-p">CICI纤美采用全新英国科技纤美理念的健康美容机构。2011年正式进入中国，集团总部及旗舰店设立在中国的经济中心-上海。CICI纤美采用全套高科技塑形和美颜仪器，配合高素质的专业纤美顾问，旨在为每位选择CICI纤美的女性带来全方位、高科技的塑形和美颜体验。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/5/67/17d/7564/186949f54c88fcd7da0c4c.jpg" />
            </van-collapse-item>
            <van-collapse-item title="嘻哈帮 B-203-204 " name="3">
              <img class="collapse-img" src="https://p1.dodoca.com/org/9/e7/e3e/f3d4/78c9f20f9262372607c57d.jpg" />
              <div>
                <p class="collapse-p">嘻哈帮创建于2007年，集团总部位于北京三里屯SOHO。嘻哈帮是国内首家致力于HIPHOP时尚产业文化传播与商业发展的创新型机构，业务涉及专业街舞培训、赛事活动、童星经纪、潮牌服饰、少年艺人培养等领域。嘻哈帮街舞，专业致力于少儿及青少年街舞艺术教育培训，是中国街舞培训行业领军品牌。在北京、上海、河南、山东、湖南、浙江、河北等5省2市设有多家分支机构，全国超50家校区规模，累计培训学员超300万人次，专业赛事奖项达1000余次，教师团队人数超200人。中国舞蹈家协会授予嘻哈帮街舞五星级教学会员单位荣誉称号。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/9d/b1d/f94c/57d63058d773e992fb0b89.jpg" />
            </van-collapse-item>
            <van-collapse-item title="原物炙烧烤 B-205" name="4">
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/10/82c/f363/d47eeb41c9bce128d8f93d.jpg" />
              <div>
                <p class="collapse-p">原物炙是一家时尚烧烤店的名称，以真味道，自然源作为其品牌理念，力求传承这一原始的饮食文明。我们充分尊重食物本真的味道，烤品种类新奇，丰富：护心肉系列，吊炉系列等，味道原汁，醇正如旷野闻炙鲜肉于石。一定会刷新您对烧烤的认识。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/64/ac5/d1d0/a780ee21dceda9769292ae.jpg" />
            </van-collapse-item>
            <van-collapse-item title="大鼓米线 B-206" name="3">
              <img class="collapse-img" src="https://p1.dodoca.com/org/4/bd/2c8/ea28/bdfc87c1675581ebf3e3db.png" />
              <div>
                <p class="collapse-p">大鼓米线精选猪筒骨经数十小时高温熬制成鲜美浓汤，外加十余种食材搭配，滋养补气！共同呈现大骨浓汤的元气本色！与你分享美味时刻！</p>
                <p class="collapse-p">在“健康轻食类”食品风潮的推动下，“大鼓”在原有的饮料基础上，新增了“青柠乳酸菌”“缤纷水果茶”，两款健康果饮。富含维生素C的青柠与“健康食品”之称的乳酸菌，</p>
                <p class="collapse-p">补充营养，协助肠胃蠕动；各类水果组成的“缤纷水果茶”，多种维生素的汇集饮品，必定给你口感的惊喜与满足！</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/64/ac5/d1d0/a780ee21dceda9769292ae.jpg" />
            </van-collapse-item>
            <van-collapse-item title="虾满堂 B-207-208" name="4">
              <img class="collapse-img" src="https://p1.dodoca.com/org/1/79/af0/338d/5193a1c2d936ec763a4fc9.png" />
              <div>
                <p class="collapse-p">虾满堂四季产品为小龙虾；其主打特色有十三香龙虾、精品黄金龙虾、黄焖龙虾、啤酒龙虾、冰镇龙虾等各种口味；  虾满堂秋冬有特色蟹类：黄焖与香辣大闸蟹、烧烤系列、羊肉系列等；尤其是老北京的传统羊蝎子火锅；羊排、鲜切羊肉是冬季主推产品。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/32/78a/8d0e/c71a19c476f5cd9f47c153.jpg" />
            </van-collapse-item>
            <van-collapse-item title="汤先生汤馆 B-209" name="5">
              <div>
                <p class="collapse-p">Soup Master 汤先生 - 专注于无添加养生汤的消费升级品牌。针对快节奏的都市人对健康品质生活的追求，提供原盅蒸炖四小时、不添加味精鸡精的养生汤，把一盅妈妈爱心汤的味道带给更多在外打拼的人。通过多元消费场景，如早午晚餐、下午茶，打造全新体验的纯白汤馆，研发独家定制的便携包装，重新定义喝汤这件事儿，将传统的养生汤用更年轻、便捷、温暖的方式触达顾客。</p>
                <p class="collapse-p">让喝汤成为一种生活方式。无添加的养生汤，只为懂生活的你。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/eb/3e6/1db2/915b2cf8dd1f19bf476e84.jpg" />
            </van-collapse-item>
            <van-collapse-item title="小陈老师茗茶馆 B-210-211" name="6">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/bkUFXke2_OVH4.png" />
              <div>
                <p class="collapse-p">小陈老师茗茶馆，装修的很有味道，小桥流水的江南韵味和个性艺术的茶马风情，给人温馨舒适的感觉；茶馆亲切温柔的服务更是让人倍感亲切。单点一份茶水，小食水果可畅食，是一家值得小坐的茶馆。非常适宜商务会谈、休闲小憩、情侣约会、朋友聚会等。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/06/d98/8ce0/41de730e487e0b84acf3cf.jpg" />
            </van-collapse-item>
            <van-collapse-item title="溪雨观 B-212-213" name="7">
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/4d/500/f77a/786335b4a7ec72a46b0ea7.jpg" />
              <div>
                <p class="collapse-p">溪水清清，溪里鱼儿欢游，一直是创始人饶胜君董事长儿时的记忆。在四川，几乎家家都会腌酸菜，人人都会做酸菜鱼。定居在上海的饶胜君董事长，为了追寻记忆中的那份酸爽与天然，携团队创立了“溪雨观 ”酸菜鱼。</p>
                <p class="collapse-p">从云，贵，川，到大江南北，遍访食材，近百次的试验调整，终得独家风味。酸菜，要用中国泡菜之乡成都一带的千年工艺，选取20年陈的陈年老坛泡菜，老卤自然乳酸菌发酵满200天，这样的酸菜更脆爽，酸味更醇厚；鱼，以精选优质黑鱼为佳，切成薄片，Q弹鲜嫩；汤，以猪简骨、鱼骨等，佐天然香料，共计10余味，熬成高汤，鲜美滋补。酸菜、溪鱼、高汤的配伍，方得溪鱼观酸菜鱼独特的开胃酸爽。</p>
                <p class="collapse-p">溪鱼观品牌始创于2014年，即创造行业奇迹，加盟商络绎不绝，现已拥有百家门店，被同行称为“溪鱼观现象”。但公司董事长深知，要赢得持续的胜利，必须时刻回归初心，不断地追寻溪水清清、溪鱼欢游的那份天然，把对自然、对人的关爱落到更深处。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/a8/7c1/5653/ae9f7282db796ee3d61d9f.jpg" />
            </van-collapse-item>
            <van-collapse-item title="Oxford Path 牛津英语课程 B-214-215" name="8">
              <img class="collapse-img" src="http://p1.dodoca.com/org/a/f1/3f1/4e71/3070f4a42e5df4665fc5b2.jpg" />
              <div>
                <p class="collapse-p">从现在起，你的孩子也可以足不出沪，轻松学习来自英国牛津大学的英语课程！Oxford Path是目前唯一将全套牛津英语课程带来上海的正规培训机构。课程的所有教材、授课方式、教师培训及考核均由牛津大学出版社直接管理。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/25/807/84a9/ecd4badba90e868e836279.jpg" />
            </van-collapse-item>
            <van-collapse-item title="食特美烧肉日本料理 B-216" name="9">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/xJ69ZgiA_DF27.jpg" />
              <div>
                <p class="collapse-p">在上海如果要说烧肉的话应该有很多人马上会想到在上海颇有名气的食特美日式烧肉吧！座落于浦东新区樱花路80弄2楼216号的全新店面开业在即。品质、味道的承载、当然也少不了一贯的优惠价格，总是在一天工作之余的首选及热点话题。本店NO1人气商品当然要数曾在2008年北京奥运会时拿过冠军的”和牛冠军牛排”、以及独家秘制的”壶装腌制牛排”、和”牛赤身肉组合”！本店保证让您吃到正宗日式烧肉的味道！独家秘制的酱汁配上澳洲黑毛和牛牛肉简直美味到没有朋友！</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/f/53/298/5d3b/68dc38863184817e27c186.jpg" />
            </van-collapse-item>
            <van-collapse-item title="昂立智立方 B-217" name="10">
              <img class="collapse-img" src="https://p1.dodoca.com/org/2/41/a79/2bc9/5e52700e3d538b3a1cc4b3.jpg" />
              <div>
                <p class="collapse-p">作为昂立旗下中学生教育高端品牌，智立方教育由上海交通大学控股，是国内A股市场教育第一股。为中学生提供高效的“1对1”和“1对3”两种辅导形式，覆盖小升初”至“高三”全科辅导。同步、强化、应试、单项突破、学科竞赛……针对性教学体系，通过对学生进行入学能力测试，量身定制成长方案，帮助孩子在智力成长、学习力成长和品格成长方面，获得360°成长。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/a3/da0/231b/5496588d1f566219fd135a.jpg" />
            </van-collapse-item>
            <van-collapse-item title="昂立小法狮 B-218-219" name="11">
              <div>
                <p class="collapse-p">小法狮是昂立教育集团旗下幼少儿教育品牌，昂立智立方兄弟品牌，专注培养、开发3-12岁幼少儿全脑思维能力课程。全脑思维能力课程以培养孩子数学思维能力、语言思维能力、综合品格能力为核心，轻松应对幼升小。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/c9/627/c67f/5daad9d15d02141d137bae.jpg" />
            </van-collapse-item>
            <van-collapse-item title="古都鸭王 B-220-221" name="12">
              <img class="collapse-img" src="https://p1.dodoca.com/org/6/8f/2a4/538c/87d764f863ced083de4eca.png" />
              <div>
                <p class="collapse-p">古都鸭王主营地道陕西菜。从陕西博大的美食中甄选了一批具有代表性的民间菜、官府菜、宫廷菜。 古都鸭王精选的陕味民间菜，流传三秦大地数千年，点滴之间，尽显中华美食的历史沧桑。经大厨运用特殊技法秘制改良。传承醇正陕味，独具妙法匠心。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/2/5e/3af/cb6a/897c4b6e8d7d34c3d3f355.jpg" />
            </van-collapse-item>
            <van-collapse-item title="巴黎衣柜 B-222" name="13">
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/4a/a02/341c/afee848776b94446069d67.jpg" />
              <div>
                <p class="collapse-p">巴黎衣柜是上海玟诺服饰旗下一个快速成长中的时尚买手品牌，主营中高端女性外贸服饰，公司有专业的买手团队走遍时尚的每个角落，为您搜罗全球爆品，同时我们秉承的理念是让每一个懂得宠爱自己的女人更具时尚魅力！</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/02/b53/fc11/ff52906e975b3ff1df4469.jpg" />
            </van-collapse-item>
            <van-collapse-item title="棉购 B-223" name="14">
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/ba/5ef/7e26/77657b0c972d2aad91fa2a.jpg" />
              <div>
                <p class="collapse-p">棉购是一家专营居家类产品的品牌。致力于打造都市化居家生活新理念；在都市的温情中融入大自然的质朴。倡导自然、舒适、简约的生活美学。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/5/84/ab6/039c/7040ef160e6ce02a05781e.jpg" />
            </van-collapse-item>
            <van-collapse-item title="MEY时尚女装 B-225" name="15">
              <div>
                <p class="collapse-p">MEY女装专为都市女性设计各类中高档女性时装、鞋子、饰品，并致力于追求时尚精致与实用美学结合的完美平衡，通过整合国际、国内尖端时尚资讯，融会贯通，倾心专注于开发新款。秉承“优雅、大气、经典、时尚”的设计理念，运用上等的面料为现代都市女性展示自信、独立、优雅并富有女人昧的特别之处。 </p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/16/e1d/2061/e4d7c4c1a85a929b2c92dd.jpg" />
            </van-collapse-item>
            <van-collapse-item title="米莱匠奢侈品皮革护理 B-226" name="16">
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/e1/9e7/dd98/c39fdbc24f78909200fb6a.png" />
              <div>
                <p class="collapse-p">米莱匠奢侈品皮革护理，源自南京西路伊势丹维修中心，专注皮具护理十年，支持各大品牌鞋子、包包、皮衣等各类皮革制品清洗、翻新、维修、配件等服务。米莱匠，传承匠人精神，用心做好每一个鞋包。逆转时光，再现惊喜！（支持上门取送）</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/b/76/11f/fb41/ff38c56eda02714cb52b8f.jpg" />
            </van-collapse-item>
            <van-collapse-item title="服生若梦 B-226-1" name="17">
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/b1/b94/3cb4/78344fec3e48472c37385c.jpg" />
              <div>
                <p class="collapse-p">服生若梦是一家集精选与原创为一体的服装生活馆。我们主打“简约，而不简单”的品牌理念，主要出售服装为主的产品，配以各美学类商品。我们通过优质独特的产品和服务为每一位顾客带来展示自身态度和审美的选择。</p>
              </div>
            </van-collapse-item>
            <van-collapse-item title="柒叁 B-227" name="18">
            </van-collapse-item>
            <van-collapse-item title="鑫城堡 B-228" name="19">
            </van-collapse-item>
            <div style="height: 50px;"></div>
          </van-collapse>
        </div>
      </van-tab>
      <van-tab title="商业3F">
        <div>
          <van-collapse v-model="activeNames3F" accordion>
            <van-collapse-item title="威尔士健身会所 B-301" name="1">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/3TPPhb5B_Z7Sf.png" />
              <div>
                <p class="collapse-p">威尔士立足于健康产业，不仅仅秉承健身这一简单理念，而是力求将健康生活方式带到中国，将健康理念渗透到会员的工作及生活当中。针对每一位会员，配备专业的私人教练团队，拥有最齐全的国际流行团操课程，与多家国际知名品牌联手，丰富的合作活动和源源不断的商品赞助为会所增加人气</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/9f/333/1788/6f75ba72f9dc1567f014bf.jpg" />
            </van-collapse-item>
            <van-collapse-item title="晓乐棋牌会所 B-301-1" name="2">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/WEahuMZd_AHGf.png" />
              <div>
                <p class="collapse-p">晓乐棋牌全独立包房，豪华装修，环境舒适，带给您不一样的娱乐体验。 爱生活、爱娱乐、晓乐棋牌，您最佳的选择！</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/9/88/926/f6b5/d45e0aada6e35ef8a4a239.jpg" />
            </van-collapse-item>
            <van-collapse-item title="山城辣妹子火锅 B-302-303" name="3">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/gxoPt4so_9h1R.png" />
              <div>
                <p class="collapse-p">北京山城辣妹子餐饮服务有限公司成立至今已有二十三年历史，是一家以经营正宗重庆鸳鸯火锅为主的餐饮连锁名店。山城辣妹子火锅原料的熬制秘方均来自重庆本地，使客人品尝到了原汁原味的山城火锅的味道——麻、辣、鲜、香！十余种独有的免费特色小吃更是吸引了众多食客纷纷慕名而至，山城辣妹子面向大众，以热情、周到、贴心的服务和物美价廉、令人回味的菜肴，博得了广大顾客的一致好评。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/4/08/9da/be83/51167b019374765fa8f2e9.jpg" />
            </van-collapse-item>
            <van-collapse-item title="至慧学堂 B-304" name="4">
              <img class="collapse-img" src="http://p1.dodoca.com/org/3/2b/4e1/c059/27f7377403db560eb564b9.png" />
              <div>
                <p class="collapse-p">是中国少儿思维培养的领军者，由哈佛、北大精英创立，专注并致力于3-10岁少儿数学思维能力的培养。创新的“i至慧•多元趣味教学体系”，采用“哈佛案例教学法”，小班授课，强调主动学习及课堂互动，迅速提升孩子的学习兴趣并拓展其数学思维能力。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/4/08/9da/be83/51167b019374765fa8f2e9.jpg" />
            </van-collapse-item>
            <van-collapse-item title="婴智贝佳早教中心 B-305-306" name="5">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/NKTxQR7Q_EiaS.png" />
              <div>
                <p class="collapse-p">贝佳创立于2003年，是上海市首批教育局认证有办学资质的专业早教机构。在上海，婴智贝佳被计生委指定“0-3岁婴幼儿早教实验基地”。婴智贝佳的商标经国家商标局注册认证。成立10年来，婴智贝佳已服务143295+宝宝家庭。于2005年，婴智贝佳与北京师范大学、中国科学院心理研究所、华东师范大学、上海师范大学、复旦大学出版社建立合作研发团队，研发出全国首创平衡教育（Balance of Early Education）领导品牌。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/2/e3/190/0c05/d46910ddadc7385bdb8f32.jpg" />
            </van-collapse-item>
            <van-collapse-item title="巧虎KIDS B-307-308" name="6">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/6k0cACjp_wbkX.png" />
              <div>
                <p class="collapse-p">我们希望孩子能有简单快乐的童年时期，不仅能够健康全面的成长，还能远离压力！为了给孩子创造这样的环境和美好的童年回忆。 巧虎KIDS是"乐智小天地"的衍生服务，为了让孩子们可以在巧虎的陪伴下快乐成长，秉承着乐智小天地“全方位学习”的理念，衍生出的实体学习基地——巧虎KIDS。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/d/b5/bb6/170d/56979e0d0495e86ca49c5d.jpg" />
            </van-collapse-item>
            <van-collapse-item title="学大教育 B-309" name="7">
              <img class="collapse-img" src="http://image.135editor.com/files/users/89/895686/201609/ZixSclrT_SmEL.png" />
              <div>
                <p class="collapse-p">学大教育是目前国内个性化教育的领导者！先后帮助30多万中小学生实现了素质和成绩的突破，考上了理想的中学和大学，得到家长的一致好评！14年品牌，全国连锁；1对1个性化教育，领军品牌；独创PPTS高效辅导体系；6对1服务模式；国际权威机构，高度认可学大；优质品牌，国内主流媒体纷纷报道。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/e/80/4a8/40a6/3d5dc5a8bde195d4ae7278.jpg" />
            </van-collapse-item>
            <van-collapse-item title="WPlus儿童摄影 B-310" name="8">
              <img class="collapse-img" src="https://p1.dodoca.com/org/0/08/aae/5802/04278b0aeafe0e70961d93.jpg" />
              <img class="collapse-img" src="https://p1.dodoca.com/org/7/c7/b25/c900/91ea3ae60764244238e250.jpg" />
              <div>
                <p class="collapse-p"><strong>WPlus Studio来自韩国高端品牌摄影,成立于1982年。</strong></p>
                <p class="collapse-p">韩国30年品牌历史，于2012年以全新概念正式登陆上海。</p>
                <p class="collapse-p"><strong>明星首选</strong></p>
                <p class="collapse-p">W+被称为“明星”摄影工作室，经常可以在拍摄时遇到明星，自己也能体验到明星般的服务感受。</p>
                <p class="collapse-p"><strong>国际大师摄影团队</strong></p>
                <p class="collapse-p">美国PPA认证，韩籍摄影大师，客照亲自拍摄。</p>
                <p class="collapse-p"><strong>顶级品牌的童装服饰</strong></p>
                <p class="collapse-p">店内童装服饰由韩国专业服装搭配师在韩国和欧洲市场亲自采购</p>
                <p class="collapse-p">大众点评网连续5年人气第一商户</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/17/bef/21c2/55b84e3e4a419fce2df8d3.jpg" />
            </van-collapse-item>
            <van-collapse-item title="慈幼儿童健康生活馆 B-312-313" name="9">
              <img class="collapse-img" src="https://p1.dodoca.com/org/8/3d/008/576c/c15cf2405a71f051358d15.jpg" />
              <div>
                <p class="collapse-p">慈幼儿童健康生活馆由上海中医大教授、博士及三甲医院医生创立。为0-12岁儿童提供专业小儿推拿保健服务的连锁机构。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/3/dd/510/a25b/72e5735c37896aeea2a7d1.jpg" />
            </van-collapse-item>
            <van-collapse-item title="奥琳欣草本护发 B-315-316" name="10">
              <img class="collapse-img" src="https://p1.dodoca.com/org/2/b7/f6a/e06d/e2b907912e9b622b86eadb.jpg" />
              <div>
                <p class="collapse-p">草药护发Q&A:坚持采用纯天然且最高品质的中草药材作为原料，100%无任何化学成分及防腐剂，草药护理不但可以改善头皮的脱发、头皮屑、出油等问题，还可以通过护理达到部分已有的白发，通过增加黑色素转变为黑发。已经有白发的毛囊，护理一段时间，另外生长出黑发。其产品通过新加坡政府HSA检验及认证，小孩和孕妇也可以使用。</p>
              </div>
              <img class="collapse-img" src="https://p1.dodoca.com/org/9/00/313/f772/805bbaef6e06ae2b360c53.jpg" />
            </van-collapse-item>
          </van-collapse>
        </div>
      </van-tab>
    </van-tabs>
    <van-tabbar v-model="active">
      <van-tabbar-item icon="shop-collect" to="shop">商城</van-tabbar-item>
      <van-tabbar-item icon="setting" to="introduction">走进富荟</van-tabbar-item>
      <van-tabbar-item icon="credit-pay" to="card">卡券</van-tabbar-item>
      <van-tabbar-item icon="contact" to="home">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
export default {
  data() {
    return {
      active: 1,
      tabsActive: 0,
      activeNamesB1: '1',
      activeNames1F: '1',
      activeNames2F: '1',
      activeNames3F: '1',
    }
  }
}
</script>
<style>
.collapse-img{
  width: 100%;
}
.collapse-p{
  font-size: 14px;
  color: #666;
}
</style>
