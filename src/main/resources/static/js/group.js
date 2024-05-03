document.addEventListener("DOMContentLoaded", function () {
    var invitation_btn = document.getElementById("create-invitation-link");
    var groupId = invitation_btn.getAttribute("data-id");

    invitation_btn.addEventListener("click", function () {

        fetch(`/createGroup/${groupId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                return response.text();
            })
            .then(data => {
                console.log("Response:", data);
                // Traitez la réponse ici si nécessaire
            })
            .catch(error => {
                console.error("There was a problem with the fetch operation:", error);
            });
    });
});
