package ru.kpfu.itis.oop.list;

/**
 * Двунаправленный список, где каждый элемент содержит ссылки на предыдущий и следующий элементы. 
 * 
 * см. объяснение наследования в классе ArrayList
 */
public class LinkedList extends AbstractList {
	//первый элемент списка
	private Node first;
	//последний элемент списка
	private Node last;

	/**
	 * Класс элемента списка
	 * 
	 * Определение класса может размещаться внутри определения другого класса. 
	 * Такие классы называются внутренними(inner class)
	 * 
	 *  Мы поместили класс Node внутрь класса LinkedList, потому что элемент списка принадлежит списку.
	 *  Мы сделали класс Node закрытым(private), чтобы никто вне класса LinkedList не имел к нему доступ.
	 *  Ключевое слово static указывает на то, что класс Node является статическим. Мы сделали его статическим, 
	 *  поскольку в классе Node не используются никакие члены внешнего класса и для создания объекта типа Node 
	 *  не нужен объект внешнего класса.
	 *  Статические внутренние классы называются вложенными (nested).
	 *
	 */
	private static class Node {
		//ссылка на предыдущий элемент списка
		Node prev;
		//ссылка на следующий элемент списка
		Node next;
		//данные текущего элемента
		Object data;
		
		//конструктор класса Node с тремя параметрами
		public Node(Node prev, Object data, Node next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * Метод получения значения элемента по индексу
	 */
	public Object get(int i) {
		Node x = getNode(i);
		return x == null ? null : x.data;
	}

	/**
	 * Метод поиска элемента списка с заданным индексом.
	 * @param i Индекс искомого элемента
	 * @return Найденый элемент списка, либо null, если элемент не был найден
	 * 
	 * Модификатор доступа private указывает на то, что данный метод доступен только в текущем классе.
	 * Это вспомогательный метод, мы не хотим, чтобы пользователям он был виден.
	 */
	private Node getNode(int i) {
		if (i >= size) {
			return null;
		}
		//если индекс искомого элемента находится в первой половине массива
		if (i < (size >> 1)) {
			//ведем перебор с начала списка
			Node x = first;
			for (int j = 0; j < i; j++) {
				//сдвигаемся на следующий элемент списка
				x = x.next;
			}
			return x;
		} else {
			//в противном случае ведем перебор с конца списка
			Node x = last;
			for (int j = size - 1; j > i; j--) {
				//сдвигаемся на предыдущий элемент списка
				x = x.prev;
			}
			return x;
		}
	}

	/**
	 * Метод добавления элемента в конец списка
	 */
	public void add(Object o) {
		Node l = last;
		//создаем новый элемент
		Node newNode = new Node(l, o, null);
		//добавленный элемент будет последним в списке
		last = newNode;
		//проверяем, не был ли список пустым
		if (l == null) {
			//если список был пустой, то добавленный элемент будет первым
			first = newNode;
		} else {
			/*если список был не пустой, то тот элемент, 
			который был последним теперь должен содержать ссылку на добавленный элемент */ 
			l.next = newNode;
		}
		size++;
	}

	/**
	 * Метод удаления элемента по индексу
	 */
	public Object remove(int i) {
		Node x = getNode(i);
		if (x == null) {
			return null;
		}
		Object oldData = x.data;
		Node prev = x.prev;
		Node next = x.next;
		
		if (prev == null) {
			//если удаляемый элемент первый в списке, то first теперь должен ссылаться на второй элемент
			first = x.next;
		} else {
			//в противном случае тот элемент, который шел до x теперь должен ссылаться на следующий за x-ом элемент 
			prev.next = next;
			x.prev = null;
		}
		if (next == null) {
			//если удаляемый элемент последний в списке, то last теперь должен ссылаться на предпоследний элемент
			last = x.prev;
		} else {
			//в противном случае тот элемент, который шел после x теперь должен ссылаться на элемент идущий перед x
			next.prev = prev;
			x.next = null;
		}
		x.data = null;
		size--;
		return oldData;
	}
	
	/** TODO
	 * 1) Измените метод получения элемента по индексу и удаления элемента по индексу, 
	 * так чтобы они кидали ваше собственное unchecked исключение WrongIndexException, 
	 * если значение индекса выходит за границы списка.
	 * 2) Cоздайте метод, который устанавливает новое значение в ячейку списка с заданным индексом. 
	 * Метод должен выбрасывать WrongIndexException, если значение индекса выходит за границы списка.
	 */
	
	
}
