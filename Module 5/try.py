import shutil
import os

for i in range(1,35):
    os.rename(f"./Question {i}/Question{i}.json", f"./Question {i}/Problem{i}.json")