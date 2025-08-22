package system.dev.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jsonController {

	@Autowired
	JSONService jService;
	
	@GetMapping("/")
	public String getDataAll() {
		
		List<JSONPeople> listJPeople = jService.readJsonArray("people.json");
		
		StringBuilder retText = new StringBuilder("<html><body><table border=\"1\"><th>ID</th><th>名前</th><th>年齢</th><th>メール</th><th>契約形態</th>");
		for(JSONPeople jp:listJPeople) {
			 retText.append("<tr><td align=\"center\">" + String.valueOf(jp.getId()) + "</td>" +
							"<td align=\"center\">" + jp.getName() + "</td>" +
							"<td align=\"center\">" + String.valueOf(jp.getAge()) + "</td>" +
							"<td align=\"center\">" + jp.getEmail() + "</td>" +
							"<td align=\"center\">" + jp.getEmployment() + "</td></tr>");
		}
		retText.append("</table></body></html>");
		return retText.toString();
	}
	
	@GetMapping("/{id}")
	public String getDataById(@PathVariable int id) {
		
		List<JSONPeople> listJPeople = jService.readJsonArray("people.json");
		StringBuilder retText = new StringBuilder();
		
		//List型変数からstream(データの結合)を利用して条件に合致するデータのみを抽出する
		//ラムダ式による短縮系の書き方
//	    Optional<JSONPeople> rstPeople = listJPeople.stream()
//	            .filter(p -> p.getId() == id)		//フィルタする条件
//	            .findFirst();						//フィルタされたデータの一番初めのデータ
		
	    //ラムダ式を使わない場合の書き方（関数インタフェース）
//	    Optional<JSONPeople> rstPeople = listJPeople.stream()
//	            .filter(new Predicate<JSONPeople>() {
//	            	@Override
//	            	public boolean test(JSONPeople p) {
//	            		return p.getId() == id;
//	            	}
//	            })
//	            .findFirst();
	    
		if(id <= listJPeople.size()) {
			//リスト内から単純にデータを指定して取得するやり方
			JSONPeople jp = listJPeople.get(id-1); //インデックスが0から始まるため-1する
//			JSONPeople jp = rstPeople.get();
			retText.append("<html><body><table border=\"1\"><th>ID</th><th>名前</th><th>年齢</th><th>メール</th><th>契約形態</th>" +
					 "<tr><td align=\"center\">" + String.valueOf(jp.getId()) + "</td>" +
					 "<td align=\"center\">" + jp.getName() + "</td>" +
					 "<td align=\"center\">" + String.valueOf(jp.getAge()) + "</td>" +
					 "<td align=\"center\">" + jp.getEmail() + "</td>" +
					 "<td align=\"center\">" + jp.getEmployment() + "</td></tr>" +
					 "</table></body></html>");
		} else {
			retText.append("The specified ID number does not exist!!");
		}
		return retText.toString();
	}
}
