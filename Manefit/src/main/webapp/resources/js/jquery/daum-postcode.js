function openDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("pc").value = data.postcode1 +"-"+ data.postcode2;
			document.getElementById("addr1").value = data.address;
			document.getElementById("addr2").focus();
		}
	}).open();
}

function openDaumPostcode1() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("oaddr1").value ="(" + data.postcode1 +"-"+ data.postcode2 +") "+data.address;
			document.getElementById("oaddr2").focus();
		}
	}).open();
}

function openDaumPostcode2() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById("raddr1").value ="(" + data.postcode1 +"-"+ data.postcode2 +") "+data.address;
			document.getElementById("raddr2").focus();
		}
	}).open();
}