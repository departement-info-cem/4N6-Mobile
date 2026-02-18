import argparse
import re
import json
import os
import sys
import urllib.request

ID_PATTERN = re.compile(r'\d{5,20}')


def extract_ids(text: str) -> list[str]:
    return ID_PATTERN.findall(text)


def read_students_from_file(file_path: str) -> list[str]:
    if not os.path.isfile(file_path):
        print("Le chemin vers le fichier spécifié n'est pas valide")
        sys.exit(2)

    students: list[str] = []
    with open(file_path) as file:
        for line in file:
            students.extend(extract_ids(line))
    return students


def read_students_interactive() -> list[str]:
    print("Mode interactif — collez ou entrez les IDs.")
    print("Deux lignes vides consécutives ou Ctrl+D pour terminer.")
    students: list[str] = []
    empty_count = 0
    while True:
        try:
            line = input("> ")
        except EOFError:
            break
        if not line.strip():
            empty_count += 1
            if empty_count >= 2:
                break
            continue
        empty_count = 0
        students.extend(extract_ids(line))
    return students


def students_to_json(students: list[str]) -> str:
    data = [{"username": s, "password": "pass"} for s in students]
    return json.dumps(data, sort_keys=True, indent=4, separators=(',', ': '))


def main():
    parser = argparse.ArgumentParser(
        description="Générer un fichier json à partir d'une liste d'étudiants"
    )
    parser.add_argument("students", type=str, nargs='?', default=None,
                        help="Fichier contenant les IDs étudiants (optionnel)")
    args = parser.parse_args()

    if args.students:
        students = read_students_from_file(args.students)
    else:
        students = read_students_interactive()

    if not students:
        print("Aucun ID trouvé.")
        sys.exit(1)

    json_students = students_to_json(students)

    with open("output.json", "w") as json_file:
        json_file.write(json_students)
    print(json_students)

    url = "http://localhost/api/admin/student"
    req = urllib.request.Request(url, data=json_students.encode("utf-8"),
                                headers={"Content-Type": "application/json",
                                         "admin-password": "Passw0rd!"},
                                method="POST")
    try:
        with urllib.request.urlopen(req) as resp:
            print(f"\n✅ Serveur: {resp.read().decode()}")
    except urllib.error.URLError as e:
        print(f"\n❌ Impossible de contacter le serveur: {e.reason}")

if __name__ == "__main__":
    main()
