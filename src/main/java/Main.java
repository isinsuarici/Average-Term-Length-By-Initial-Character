import java.util.*;
import java.util.stream.Stream;

public class Main{
    String fileName;
    int topN=10;
    static Map<String,Double> KacKereGeciyor = new TreeMap<>();
    static String[] dizi;
    ArrayList<Double> list1 = new ArrayList<>();
    ArrayList<Double> list2 = new ArrayList<>();
    Map<Character, Double> CharacterCounter = new TreeMap<>();
    Map<Character, Double> WordLength = new TreeMap<>();
    Map<String, Double> Pairs = new TreeMap<>();
    /** İkinci metot*/
    ArrayList<Double> kelimeSayilari = new ArrayList<>();

    public Main()  {

        fileName= "18 Mayıs 2018 tarih ve 30425 sayılı Resmî Gazete'de yayımlanan 7141 sayılı Kanun ile kurulan Eskişehir Teknik Üniversitesi, bilim, kültür ve aynı zamanda  bir gençlik kenti olan Eskişehir'in merkezinde yer alan 2 yerleşkede 5 Fakülte, 2 Meslek Yüksekokulu ve 3 Enstitü'den oluşmaktadır.\n" +
                "Eskişehir Teknik Üniversitesi, en son teknolojik olanaklarla donatılmış birimlerinde, her biri alanındaki gelişmeleri takip eden ve tüm zamanını öğrencileriyle paylaşan geniş öğretim kadrosuyla, öğrencilerini yaratıcı ve dinamik eğitim ortamlarında, geleceğe en donanımlı ve rekabet edebilir olarak hazırlamak üzere çalışmaktadır.Eskişehir Teknik Üniversitesi'nin güvenli ve çağdaş yerleşkelerinde bir öğrenci için gerekli olan hemen hemen her olanak bulunmaktadır. 315.000'den fazla basılı kaynağın yanı sıra birçok e-kaynağa da ulaşmanın mümkün olduğu Anadolu Üniversitesi'nin Yunus Emre Kampüsü'ndeki Merkez Kütüphane, haftanın her günü öğrencilere 24 saat hizmet vermekte ve Eskişehir Teknik Üniversitesi öğrencileri ve personeli de kütüphanenin olanaklarından yararlanabilmektedir.\n" +
                "12.330 m2 alana yayılmış olan Merkez Kütüphane'de, öğrencilerimiz kendi çalışma biçimlerine uygun ortamları kullanabilmektedir. Öğrencilerimiz, Anadolu Üniversitesi'nin Yunus Emre Yerleşkesi'nde bulunan sinema, tiyatro, konser ve sergi salonlarında kültürel ve sanatsal etkinlikleri izleyebilmekte, sanatçılarla yapılan söyleşilere ve etkinliklere katılabilmektedir. Öğrencilerimiz İki Eylül Yerleşkesi'ndeki ve Anadolu Üniversitesi'nin Yunus Emre Yerleşkesi'ndeki tüm spor tesislerinden yararlanabilmektedir. Üniversitemiz öğrencilerine öğle yemeği servisi de sunulmaktadır.";

        //whitespace, punctuations and lowercase işlemleri
        fileName = fileName.toLowerCase();
        fileName = fileName.replace(" ", "|£#|");
        fileName = fileName.replace("\n", "|£#|");
        dizi = fileName.split("[,;:'.!?^/]");
        fileName = "";
        for (String a : dizi)
            fileName += a;
        fileName = fileName.replace("|£#|", " ");
        dizi = fileName.split(" ");
//***************************************************************

        computeAvgLengthByFirstChar();
        Set pairs = calculateMinPairDist();

        pairs.forEach(System.out::println);
    }

    private void computeAvgLengthByFirstChar() {
        for (String value : dizi) {
            if (!value.isEmpty()) {
                char c = value.charAt(0);
                if (CharacterCounter.containsKey(c))
                    CharacterCounter.replace(c, CharacterCounter.get(c) + 1.0);
                else
                    CharacterCounter.put(c, 1.0);
                if (WordLength.containsKey(c))
                    WordLength.replace(c, WordLength.get(c) + value.length());
                else
                    WordLength.put(c, (double) value.length());
            }
        }
        Collection<Double> counter = CharacterCounter.values();
        Collection<Double> length = WordLength.values();
        list1.addAll(counter);
        list2.addAll(length);
        int p = 0;
        for (Map.Entry<Character, Double> entry : WordLength.entrySet()) {
            System.out.println("Initial Character: " + entry.getKey() + "  Average Length: " + list2.get(p) / list1.get(p));
            p++;
        }
    }
    private Set calculateMinPairDist() {
        for (String s : dizi) {
            if (!s.isEmpty())
                if (KacKereGeciyor.containsKey(s))
                    KacKereGeciyor.replace(s, KacKereGeciyor.get(s) + 1.0);
                else
                    KacKereGeciyor.put(s, 1.0);
        }

        Collection<Double> kacDefaGeciyor= KacKereGeciyor.values();
        kacDefaGeciyor.forEach(t->kelimeSayilari.add(t));

        for(int i=0; i< dizi.length; i++ ) {
            if (!dizi[i].isEmpty())
                for (int j = i + 1; j < dizi.length; j++)
                    if (!dizi[j].isEmpty())
                        if (dizi[i].equals(dizi[j]))
                            continue;
                        else {
                            double distance = CalculateTotalDistance(dizi[i], dizi[j]);
                            String st = "Pair{t1='" + dizi[i] + "', t2='" + dizi[j] + "', factor";
                            if (Pairs.containsKey(st))
                                continue;
                            else
                                Pairs.put(st, distance);
                        }
        }//for
        Set set = new LinkedHashSet();
        Stream stream= Pairs.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(topN);
        stream.forEach(set::add);
        return  set;
    }
    public static double CalculateTotalDistance(String term1,String term2)  {
        double freq1 = KacKereGeciyor.get(term1);
        double freq2 = KacKereGeciyor.get(term2);
        double sum=0;
        for(int i=0; i< dizi.length; i++) {
            if(!dizi[i].equals(term1))
                continue;
            for(int j=i; j< dizi.length; j++){
                if(!dizi[j].equals(term2))
                    continue;
                sum+=(j-i);
                break;
            }
        }
        return (freq1*freq2)/(1+Math.log(sum));
    }

    public static void main(String[] args) {
        new Main();
    }
}