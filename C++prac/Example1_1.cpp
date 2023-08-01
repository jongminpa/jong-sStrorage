//#include <iostream>

/*int main(void) { 1번예제

	int val1;
	int result = 0;
	for (int i = 1; i <= 5; i++) {
		std::cout << i << "번쨰 정수 입력";
		std::cin >> val1;
		result += val1;
		val1 = 0;
	}
	std::cout << "정수의 합은: " << result << std::endl;
}*/

/*int main(void) { 2번예제
	
	char name[100];
	char phone[100];
	std::cout << "이름을 입력>>";
	std::cin >> name;

	std::cout << "전화번호를 입력";
	std::cin >> phone;

	std::cout << "당신의 이름은: " << name << std::endl;
	std::cout << "당신의 전화번호는: " << phone << std::endl;

 }*/

/*int main(void) {

	int num;
	std::cout << "출력할 단을 입력하세요: ";
	std::cin >> num;

	for (int i = 1; i <= 9; i++) {
		std::cout << num << "*" << i << "=" << num * i << std::endl;
	}
}*/

/*int main(void) { 예제 4번
	int num;
	int result = 0;
	while (true)
	{
		std::cout << "판매 금액을 만원 단위로 입력(-1 to end): ";
		std::cin >> num;

		result = 50 + (num * 0.12);
		std::cout << "이번달 급여:" << result << std::endl;
		if (num == -1)
			break;

		result = 0;
	}

}*/