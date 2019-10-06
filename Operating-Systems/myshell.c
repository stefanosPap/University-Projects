#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include  <sys/types.h>
#include <sys/wait.h>
//συνάρτηση για την εκτέλεση της εντολής
int execute(char **args){
  char *option[3];
  pid_t pid;
  int status = 0;
  int exists = 0;
  // με μία for loop ελέγχουμε αν υπάρχουν τα συμβολα ; && < > και ανάλογα πηγαίνουμε στην αντίστοιχη συνθήκη
  for (int i=0;i<100;i++){
    if (args[i] == NULL){
      break;
    }
    if (!strcmp(args[i],";") || !strcmp(args[i],"&&")){
      exists = 1;
    }
    if (!strcmp(args[i],">")){
      exists = 2;
    }
    if (!strcmp(args[i],"<")){
      exists = 3;
    }
  }
  // σε περίπτωση που δοθεί μονή εντολή π.χ. pwd , ls , ls -l εκτελείται αυτή η συνθήκη
  if (exists == 0){
    pid = fork();
    if (pid == 0){
      if ( execvp(args[0],args) == -1){
        perror("Error");
        exit(1);
      };
    }else if (pid < 0){
        perror("Error1");
    }else{
        while (wait(&status) != pid)       /* wait for completion  */;
      }
  }
  // αν δοθεί εντολή αυτής της μορφής π.χ. pwd ; pwd && pwd ... εκτελείται αυτή η συνθήκη
  else if (exists == 1) {
    //int buf = 1024;
    //args = realloc(args, buf * sizeof(char*));
    int i = 0;
    int k = 0;
    char ii[2] = {';','\0'};//αρχικοποιούμε τη συμβολοσειρά ";" για να τη συγκρίνουμε έπειτα
    char iii[3]= {'&','&','\0'};//αρχικοποιούμε τη συμβολοσειρά "&&" για να τη συγκρίνουμε έπειτα
    int flag[2] = {0,0};//ένας πίνακας δύο θέσεων διότι θα τον χρειαστούμε για να ελέγξουμε αν βρέθηκε ; ή && στον πίνακα args που περιέχει την κάθε εντολή
    int turn;
    //μπαίνουμε σε μία while loop για να κάνουμε προσπέλαση τον πίνακα args η οποία τερματίζει όταν βρεθεί NULL χαρακτήρας
    while (1){
      if (args[i] == NULL){
        break;
      }
      // συγκρίνουμε το κάθε στοιχείο για να δόυμε αν βρέθηκε ; ή && ή αν βρέθηκε NULL χαρακτήρας δηλαδή έφατσε στο τέλος του input
      if (!strcmp(args[i],ii) || !strcmp(args[i],iii) || args[i+1] == NULL){
        turn = 0;
        // ελέγχουμε αν είμαστε στην πρώτη επανάληψη γιατί θα το χρειαστούμε παρακάτω
        if (k == 0){
          turn = 1;
        }
        // στον πίνακα option αποθηκεύουμε την κάθε εντολή που διαχωρίζεται με τα ; ή && για να την εκτελέσουμε με την κατάλληλη σειρά
        option[0] = args[k];
        int j;
        for(j=k+1;j<i;j++){
          option[j] = args[j];
        }
        option[j] = NULL;

        k = i+1;// κρατάμε στη μεταβλητή κ την αρχή της επόμενης εντολής
        if (turn == 0){
            flag[0] = flag[1];
            /*η μεταβλητή flag[0] περιέχει το σύμβολο που υπάρχει πριν την εντολή και η flag[1]
            το συμβολο που υπάρχει μετά την εντολή για αυτό και αποθηκεύουμε το flag[1] στο flag[0] για κάθε εντολή εκτός της πρώτης
            */
        }
        // ο αριθμός 1 δηλώνει ότι βρέθηκε && και ο αριθμός 0 ότι βρέθηκε ;
        if (!strcmp(args[i],iii)){
            flag[1] = 1;
        }else {
            flag[1] = 0;
        }
        /*η συνθήκη για να εκτελεστεί η εντολή αποτελείται από δύο μέρη
        Το πρώτο είναι το (status == 0) που σημαίνει ότι η προηγούμενη εντολή εκτελέστηκε σωστά άρα σίγουρα θα εκτελεστεί και η επόμενη ανεξαρτήτως συμβόλου
        Το δεύετρο μέρος ειναι το (status != 0 && flag[0] == 0) που σημαίνει ότι η προηγούμενη εντολή δεν εκτελέστηκε σωστά αλλά το προηγούμενο σύμβολο ειναι ; οπότε εκτελείται και πάλι
        Σε κάθε άλλη περίπτωση η επομενη εντολή δεν εκτελείται
        */
        if ((status == 0) || (status != 0 && flag[0] == 0)){
          pid = fork();
          if (pid == 0){
            if (execvp(option[0],option) == -1){
              perror("Error3");
              exit(1);
            };
          }else if (pid < 0){
            perror("Error1");
          }else{
            do {
              waitpid(pid, &status , 0);
            }while (!WIFEXITED(status) && !WIFSIGNALED(status));

          }

        }
      }
      i++;
      if (args[i] == NULL){
        return 1;
      }


    }
    }
  return 1;
}
// συνάρτηση για να τεμαχίσουμε το input
char **split(char *line){
  char quit[] = {'q','u','i','t','\0'};
  int buf = 512;
  int row = 0;
  char *token;
  char **tokens = malloc(sizeof(char *) * buf);

  //char *tokens[120];
  //tokens[0] = (char *)malloc(sizeof(char));
  //
  // τεμαχχίζουμε το input με βάση τα κενά
  int k = 0;
  token = strtok(line," ");

  while (token != NULL){

    tokens[row] = token;
    row++;
    if (row>=buf){
      buf += 1024;
      tokens = realloc(tokens, buf * sizeof(char*));
    }
    //tokens = (char **)realloc(tokens, ++row * sizeof(char*));
    int i = strlen(token) - 1;
    // με την παρακάτω while απορρίπτουμε τα κενά στο τέλος του input μας ξεκινώντας από το τέλος του input
    while (i>=0){
                if (tokens[k][i] != '\n' && tokens[k][i] != ' '){
                    break;
                }
                else{
                    tokens[k][i] = '\0';
                }
                i--;
    }
    // με την παρακάτω while απορρίπτουμε τα κενά στην αρχή του input μας
    int j;
    while(tokens[k][0] == ' '){
                j=0;
                while(j<strlen(tokens[k])){
                    tokens[k][j]=tokens[k][j+1];
                    j++;
                }
              }
    token = strtok(NULL," ");
    //tokens[row-1] = (char *)malloc(strlen(token) * sizeof(char));
    k++;
  }
  tokens[row] = NULL;
  //συγκρίνουμε το input μας με τη συμβολοσειρά quit για να γίνει έξοδος από το shell
  if (!strcmp(line,quit)){
      exit(0);
  }
  return tokens;

}
// συνάρτηση με την οποία παίρνουμε το input του χρήστη
char *take_input(){

  int pos = 1;
  char *buffer = malloc(sizeof(char));
  int c;
//παίρνουμε κάθε χαρακτήρα της εισόδου ξεχωριστά
  while (1){
    c = getchar();
    if (c == '\n' || c == EOF){
      /*σε περίπτωση που εντοπίσουμε ότι έχει πατηθεί enter
      τοποθετούμε στο τέλος του buffer το χαρακτήρα \0
      */
      buffer[pos-1] = '\0';
      return buffer;
    }else{
      //σε κάθε άλλη περίπτωση παίρνουμε τον επόμενο χαρακτήρα και τον τοποθετούμε στο buffer
      buffer[pos-1] = c;
    }
    pos++;
    buffer = realloc(buffer, sizeof(char) * pos);// σε κάθε επανάληψη δεσμεύουμε θέση στη μνήμη για τον απόμενο χαρακτήρα
  }
}
// interactive έκδοση
void interactive(){

  char *line;
  char **args;

  do{
    // τυπώνεται επαναληπτικά το promt
    printf("papadam_8885>");
    // παίρνουμε την είσοδο με τη συνάρτηση take_input
    line = take_input();

    int count = 0;
    count = strlen(line) - 1;
    // σε περίπτωση που δωθεί input μεγαλύτερο από 512 χαρακτήρες απορρίπτεται και ζητείται εκ νέου άλλο input
    if (count > 512){
      printf("Line's limit exceeded, give me another command\n");
      continue;
    }
    //τεμαχίζουμε το input με τη συνάρτηση split
    args = split(line);
    //εκτελούμε την εντολή με τη συνάρτηση execute
    execute(args);

  }while (1);
}
// λειτουργία batch
void batch(char file[]){
// ανοίγουμε το αρχείο που δώσαμε σαν όρισμα από το shell
  FILE *batch;
  char line[512];
  char **args;
  batch = fopen(file,"r");
      if (batch == NULL){
        printf("Error : can't open file\n");
        exit(0);
      }

  if (fgets(line,513,batch)==NULL){
        exit(1);
  }
// τεμαχίζουμε την εντολή που δόθηκε στο command line με τη συνάρτηση split
  args = split(line);
// εκτελούμε την εντολή με τη συνάρτηση execute
  execute(args);
  // κλείνουμε το αρχείο
  fclose(batch);
}

int main(int argc, char **argv){
// το πρόγραμμα ξεκινάει από εδώ και σε περίπτωση που έχει δωθεί κάποιο όρισμα εκτελείται η batch έκδοση αλλιώς εκτελείται η interactive
  if (argc != 1){

    batch(argv[1]);

  }else{

    interactive();

  }
}
