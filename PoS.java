/*
Proof of Stakeを行うプログラム
*/

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

class PoS{
    int sumValue = 0;//各ノードが持つ通貨量の合計
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();//ノード番号と所持している通貨量の情報を保持
    Random rand = new Random();

    //ノード番号と所持通貨を記録する
    public void register(int nodeNum,int value){
        map.put(nodeNum,value);
        sumValue += value;
    }

    public int proof(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int result;
        for(int i = 0; i < map.size(); i++){
            result = map.get(i) / sumValue;
            list.add(result);
        }

        //以下、ルーレット選択によるPoSのマイニング成功者選択
        int max = 0;
        for(int j = 0; j < list.size(); j++) max += list.get(j);
        int temp = rand.nextInt(max);
        for(int j = 0; j < list.size(); j++){
            temp -= list.get(j);
            if(temp < 0) return j;
        }
        System.out.println("これが表示されたらおかしいゾ");
        return -1;
    }

}