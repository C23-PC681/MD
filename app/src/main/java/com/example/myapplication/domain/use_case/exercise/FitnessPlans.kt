package com.example.myapplication.domain.use_case.exercise

import com.example.myapplication.domain.model.Exercise

sealed class FitnessPlan(val exercises: List<Exercise>) {
    object FitnessPlanMuscleGain1 : FitnessPlan(
        listOf(
            Exercise(
                "Push Up",
                "3 set x 12 repetitions",
                "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2021/01/15/627127020.jpg"
            ),
            Exercise(
                "Sit Up",
                "3 set x 12 repetitions",
                "https://media.istockphoto.com/id/497990394/photo/healthy-man-doing-sit-ups-on-foor.jpg?s=612x612&w=0&k=20&c=CNtY0TnSwJEFBQIgoAplkF2AHYpSAdlQIoqtinDsEU0="
            ),
            Exercise(
                "Pull Up",
                "3 set x 12 repetitions",
                "https://cdn.shopify.com/s/files/1/1876/4703/articles/shutterstock_612717155_1000x.jpg?v=1639314260"
            ),
            Exercise(
                "Dumbbell",
                "3 set x 12 repetitions",
                "https://manofmany.com/wp-content/uploads/2020/07/Best-Dumbbell-Exercises-For-Men.jpg"
            ),
            Exercise(
                "Inchworm",
                "3 set x 12 repetitions",
                "https://i.pinimg.com/originals/d1/87/bb/d187bbf03bf04933f58046b55c8a8e8e.jpg"
            ),
            Exercise(
                "Deadlift",
                "3 set x 5 repetitions",
                "https://www.mensjournal.com/.image/t_share/MTk2MTM3MjgwNzExNTY2NDgx/the-end-heavy-workout-routine-for-bigger-gains-and-fewer-injuries.jpg"
            ),
        )
    )

    object FitnessPlanMuscleGain2 : FitnessPlan(
        listOf(
            Exercise(
                "Push Up",
                "3 set x 15 repetitions",
                "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2021/01/15/627127020.jpg"
            ),
            Exercise(
                "Sit Up",
                "3 set x 15 repetitions",
                "https://media.istockphoto.com/id/497990394/photo/healthy-man-doing-sit-ups-on-foor.jpg?s=612x612&w=0&k=20&c=CNtY0TnSwJEFBQIgoAplkF2AHYpSAdlQIoqtinDsEU0="
            ),
            Exercise(
                "Pull Up",
                "3 set x 15 repetitions",
                "https://cdn.shopify.com/s/files/1/1876/4703/articles/shutterstock_612717155_1000x.jpg?v=1639314260"
            ),
            Exercise(
                "Dumbbell",
                "3 set x 15 repetitions",
                "https://manofmany.com/wp-content/uploads/2020/07/Best-Dumbbell-Exercises-For-Men.jpg"
            ),
            Exercise(
                "Inchworm",
                "3 set x 15 repetitions",
                "https://i.pinimg.com/originals/d1/87/bb/d187bbf03bf04933f58046b55c8a8e8e.jpg"
            ),
            Exercise(
                "Deadlift",
                "3 set x 7 repetitions",
                "https://www.mensjournal.com/.image/t_share/MTk2MTM3MjgwNzExNTY2NDgx/the-end-heavy-workout-routine-for-bigger-gains-and-fewer-injuries.jpg"
            ),
        )
    )

    object FitnessPlanLoseWeight1 : FitnessPlan(
        listOf(
            Exercise(
                "Burpees",
                "3 set x 12 repetitions",
                "https://qph.cf2.quoracdn.net/main-qimg-9862e30e7cd9021ac851a67f3a64a6c3-lq"
            ),
            Exercise(
                "Bicycle Crunches",
                "3 set x 12 repetitions",
                "https://bod-blog-assets.prod.cd.beachbodyondemand.com/bod-blog/wp-content/uploads/2022/07/28153459/bicycle-crunches-960.png"
            ),
            Exercise(
                "Rowing",
                "3 set x 12 repetitions",
                "https://www.shape.com/thmb/FsbHKG8hdoB2roj0yILIRuqt44M=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/rowing-GettyImages-1213234950-2000-ede4191c66834f72a19286f230995faa.jpg"
            ),
            Exercise(
                "Lunges",
                "3 set x 12 repetitions",
                "https://www.verywellfit.com/thmb/_zn342CKyAPyZv8wOuOOebamfkg=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Verywell-44-2328705-DumbellLunge01-1589-8cd8f2dac4294549b5279f6f91e3eb41.jpg"
            ),
            Exercise(
                "Jumping Rope",
                "3 set x 12 repetitions",
                "https://static.toiimg.com/thumb/msid-70193411,width-1280,resizemode-4/70193411.jpg"
            ),
            Exercise(
                "Squats",
                "3 set x 12 repetitions",
                "https://i0.wp.com/post.healthline.com/wp-content/uploads/2020/05/man-squatting-outdoors-1296x728-header.jpg?w=1155&h=1528"
            )
        )
    )

    object FitnessPlanLoseWeight2 : FitnessPlan(
        listOf(
            Exercise(
                "Burpees",
                "3 set x 15 repetitions",
                "https://qph.cf2.quoracdn.net/main-qimg-9862e30e7cd9021ac851a67f3a64a6c3-lq"
            ),
            Exercise(
                "Bicycle Crunches",
                "3 set x 15 repetitions",
                "https://bod-blog-assets.prod.cd.beachbodyondemand.com/bod-blog/wp-content/uploads/2022/07/28153459/bicycle-crunches-960.png"
            ),
            Exercise(
                "Rowing",
                "3 set x 15 repetitions",
                "https://www.shape.com/thmb/FsbHKG8hdoB2roj0yILIRuqt44M=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/rowing-GettyImages-1213234950-2000-ede4191c66834f72a19286f230995faa.jpg"
            ),
            Exercise(
                "Lunges",
                "3 set x 15 repetitions",
                "https://www.verywellfit.com/thmb/_zn342CKyAPyZv8wOuOOebamfkg=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Verywell-44-2328705-DumbellLunge01-1589-8cd8f2dac4294549b5279f6f91e3eb41.jpg"
            ),
            Exercise(
                "Jumping Rope",
                "3 set x 15 repetitions",
                "https://static.toiimg.com/thumb/msid-70193411,width-1280,resizemode-4/70193411.jpg"
            ),
            Exercise(
                "Squats",
                "3 set x 15 repetitions",
                "https://i0.wp.com/post.healthline.com/wp-content/uploads/2020/05/man-squatting-outdoors-1296x728-header.jpg?w=1155&h=1528"
            )
        )
    )
}
