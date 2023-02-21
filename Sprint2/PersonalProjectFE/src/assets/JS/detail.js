var imgFeature = document.querySelector('.img-feature');
var listImg = document.querySelectorAll('.list-img img');
var prevBtn = document.querySelector('.prev');
var nextBtn = document.querySelector('.next');
console.log(listImg)
/*Cách 1: Sử dụng arrow function + e.targert để thao tác với toàn list img*/
// listImg.forEach(imgEle => {
//     imgEle.addEventListener('click', e => {
//         /* Thực hiện hành động với ảnh đang thao tác*/
//         imgFeature.src = e.target.getAttribute('src');
//         console.log(imgFeature.src);
//     })
// })
/* Cách 2: Sử dụng index của mảng listImg và function để tái sử dụng*/
var currentIndex = 0;

function updateImgByIndex(index) {
  /* remove active class */
  document.querySelectorAll('.list-img div').forEach(item => {
    item.classList.remove('active');
  })
  currentIndex = index;
  imgFeature.src = listImg[index].getAttribute('src');
  /* 'active' là thẻ con nằm trong list-img nên phải . tới parentElement mới tagert vào đc*/
  listImg[index].parentElement.classList.add('active');
}

listImg.forEach((imgEle, index) => {
  imgEle.addEventListener('click', e => {
    imgFeature.style.opacity = '0';
    setTimeout(() => {
      updateImgByIndex(index);
      imgFeature.style.opacity = '1';
    }, 500)
  })
})
prevBtn.addEventListener('click', e => {
  if (currentIndex === 0) {
    currentIndex = listImg.length - 1;
  } else {
    currentIndex--;
  }
  imgFeature.style.animation = ''
  setTimeout(() => {
    updateImgByIndex(currentIndex);
    imgFeature.style.animation = 'slideleft 1s ease-in-out forwards'
  }, 200)
})
nextBtn.addEventListener('click', e => {
  if (currentIndex === listImg.length - 1) {
    currentIndex = 0;
  } else {
    currentIndex++;
  }
  imgFeature.style.animation = ''
  setTimeout(() => {
    updateImgByIndex(currentIndex);
    imgFeature.style.animation = 'slideright 1s ease-in-out forwards'
  }, 200)
})
