import styles from "./Left.module.css"
function LeftNav(){
    return(
    <>
    <div className="Left "> 
    <div className={styles.content}>
    <div class="accordion " id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        Intro
      </button>
    </h2>
    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
      <div class="accordion-body">
      <ol><a href="">Installation</a></ol>
      <ol><a href="">Syntax</a></ol>
      </div>
    </div>
  </div>



</div>
      
    </div>

 
    </div>
        
    </>
    
    );
    }
    export default LeftNav;