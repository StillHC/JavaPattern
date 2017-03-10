//抽象类：目标接口
interface ScoreOperation{
	public int [] sort(int array[]);//成绩排序
	public int search(int array[], int key); //成绩查找
}

//快速排序：适配者
class QuickSort{
	public int [] quickSort(int array[]){
		sort(array, 0, array.lenth-1);
		return array;
	}

	public void sort(int array[], int p, int r){
		int 
	}
}