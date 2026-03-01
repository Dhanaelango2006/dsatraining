STEP 1 — Create GitHub Classroom

Go to:
https://classroom.github.com

Click Sign in

Click New classroom

It will ask:

“Where should we create this classroom?”

Choose:
👉 Your personal account (thiravugalcodetraining)

GitHub will automatically create a hidden organization for classroom repos.

STEP 2 — Create Assignment

Inside Classroom → Click New Assignment

Choose:
👉 Individual assignment

Assignment name:

OOPS Assignment 1

Starter code:
Select:

thiravugalcodetraining/dsatraining

Visibility:
Private (recommended)

Click Create Assignment

STEP 3 — Enable Codespaces for the Assignment

In assignment settings:

Enable Codespaces

Default machine: 2-core (keep cost lower)

STEP 4 — Share the Invite Link

After creation, Classroom gives you:

Assignment invitation link

That is what you send to students.

NOT:

Your repo URL

Not a Codespace URL

🎓 What Happens for Students

When they click the link:

GitHub creates a new repo for them:

dsatraining-studentname

They open:

Code → Codespaces → Create codespace

They work in their own repo

They push

You grade their repo

No one overwrites anyone.

🔥 Why This Is Safe

Your original repo:

thiravugalcodetraining/dsatraining

Becomes read-only starter template.

Students work in:

github-classroom/.../student-repo

Completely isolated.

⚠️ Important Before You Create Assignment

Make sure your base repo:

Has .devcontainer/devcontainer.json

Has Java file

Has README instructions

Because that content will be copied into every student repo.
