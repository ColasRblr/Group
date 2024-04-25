document.addEventListener("DOMContentLoaded", function () {
    var userNumberInput = document.getElementById("userNumber");
    var groupNumberInput = document.getElementById("groupNumber");
    var divUser = document.getElementById("info_form_user");
    var divLast = document.getElementById("info_form_last");

    userNumberInput.addEventListener("change", (event) => {
        var newValue = event.target.value;
        divUser.innerHTML = "";
        for (i = 1; i <= newValue; i++) {
            divUser.innerHTML += `
            <h3>Utilisateur n°${i}</h3>
            <div class="mb-3">
              <label for="firstname${i}" class="form-label">Prénom</label>
              <input type="text" class="form-control" id="firstname${i}" name="users[${i - 1}].firstname">
            </div>
            <div class="mb-3">
              <label for="lastname${i}" class="form-label">Nom</label>
              <input type="text" class="form-control" id="lastname${i}" name="users[${i - 1}].lastname">
            </div>
            <div class="mb-3">
              <label for="email${i}" class="form-label">Email</label>
              <input type="email" class="form-control" id="email${i}" name="users[${i - 1}].email">
            </div>
            <div class="mb-3">
              <label for="password${i}" class="form-label">Mot de passe</label>
              <input type="text" class="form-control" id="password${i}" name="users[${i - 1}].password">
            </div>
            <input type="hidden" name="users[${i - 1}].isAdmin" value="false">
            `
        }

        if ((newValue % groupNumberInput.value !== 0) && (groupNumberInput.value)) {
            divLast.innerHTML = `
            <div class="mb-3">
                <label for="configuration-select" class="mb-1">Choisissez la configuration des groupes</label>
                <select id="configuration-select" class="form-select" th:field="*{configuration}">
                    <option value="">--Choisissez une option--</option>
                    <option th:value="'last_min'">LAST_MIN</option>
                    <option th:value="'last_max'">LAST_MAX</option>
                </select>
            </div>
            `
        } else {
            divLast.innerHTML = "";
        }
    });

    groupNumberInput.addEventListener("change", (event) => {
        var groupValue = event.target.value;
        divLast.innerHTML = "";

        if ((userNumberInput.value % groupValue !== 0) && (userNumberInput.value !== 0)) {
            divLast.innerHTML = `
            <div class="mb-3">
                <label for="pet-select" class="mb-1">Choisissez la configuration des groupes</label>
                <select id="pet-select" class="form-select">
                    <option value="">--Choisissez une option--</option>
                    <option value="last_min">LAST_MIN</option>
                    <option value="last_max">LAST_MAX</option>
                </select>
            </div>
            `
        } else {
            divLast.innerHTML = "";
        }
    });
});
