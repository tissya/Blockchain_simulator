/*Proof of Work を行うプログラム
ただし前提として「CPUパワーがノード全体で同じ」であること
*/

import java.util.ArrayList;
import java.util.Collections;

class PoW{
    private int nodes = 0; //ノード数
    private int distributer = 0; //配信者番号
    private int sendCounter = 0; //どれだけPoWを行ったかをカウント
    ArrayList<Integer> distributorList = new ArrayList<Integer>(); //配信者リスト

    //コンストラクタ
    PoW(int nodes){
        this.nodes = nodes;
        for(int i = 0;i < nodes;i++){
            distributorList.add(i);
        }
        Collections.shuffle(distributorList); //Listの中身をシャッフルする
    }

    //PoWを行って配信者を決定するメソッド
    protected int pow(){
        if(sendCounter >= nodes){
            sendCounter = 0;
            Collections.shuffle(distributorList);
        }
        distributor = distributorList.get(sendCounter);
        sendCounter++;
        System.out.println("配信者番号 : " + distributor); //テスト用

        return distributer;
    }
}