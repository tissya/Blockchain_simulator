/**
Proof of Stakeを行うプログラム
*/

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

class PoI{
    private int nodeNum = 0;
    private int trial = 0;
    private int sumValue = 0; //すべてのノードが持つ通貨の合計

    public PoI(int nodeNum, int trial) {
        setNodeNum(nodeNum);
        setTrial(trial);
    }

    public void run(){
        Node[] node = new Node[nodeNum];
        List<Integer> lottery = new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        int excavator = 0;
        for(int i = 0 ; i < nodeNum ; i++){
            node[i].setCurrency(paw(i));
            //信頼度を入れる
            sumValue += paw(i);
            lottery.add(node[i].getCurrency());
        }

        /**
         * PoIシステムの試行
         */
        for(int j = 0 ; j < trial ; j++){
        excavator = proof(lottery);
        node[excavator].makeBlock();
        }

        /**
         * 試行終了後に各ノードが生成したブロック数をリストに突っ込む
         */
        for(int i = 0; i < nodeNum ; i++) result.add(node[i].getMakeBlocks());
        
        return;
    }

    /**
     * ブロック生成者の選定
     * @param list
     * @return
     */
    public int proof(List list){
        //以下、ルーレット選択によるPoSのマイニング成功者選択
        Random random = new Random();
        int hit = random.nextInt(sumValue);

        for(int j = 0; j < list.size(); j++){
            hit -= list.get(j);
            if(hit < 0) return j;
        }
        System.out.println("これが表示されたらおかしいゾ");
        return -1;
    }

    /**
     * べき乗を計算する
     * @param ex
     * @return
     */
    public int pow(int ex){
        int ans = 2;
        int exp = 2;
        for(int i = 1 ; i < ex ; i++){
            ans = ans * exp;
        }
        return ans;
    }

    public int getNodeNum() {
        return this.nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public int getTrial() {
        return this.trial;
    }

    public void setTrial(int trial) {
        this.trial = trial;
    }

    public int getSumValue() {
        return this.sumValue;
    }

    public void setSumValue(int sumValue) {
        this.sumValue = sumValue;
    }

}